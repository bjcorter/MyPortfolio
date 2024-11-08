import pygame
import random
import math
import sys

# Initialize Pygame
pygame.init()
pygame.mixer.init()

#Load audio files
laser_channel = pygame.mixer.Channel(0)
music_channel = pygame.mixer.Channel(1)
music = pygame.mixer.Sound('gamemusic.wav')
music.set_volume(.3)
laser_sound = pygame.mixer.Sound('laser1.wav')
laser_sound.set_volume(.4)
tie_laser_sound = pygame.mixer.Sound('tielaser.wav')
tie_laser_sound.set_volume(.4)

music_channel.play(music,-1)

num_channels = 32
pygame.mixer.set_num_channels(num_channels)
enemy_channels = [pygame.mixer.Channel(i) for i in range(2, num_channels)]

# Screen dimensions and settings
info = pygame.display.Info()
WIDTH, HEIGHT = info.current_w, info.current_h
screen = pygame.display.set_mode((WIDTH, HEIGHT), pygame.NOFRAME)
pygame.display.set_caption("newgame")
pygame.mouse.set_visible(False)

# Colors
WHITE = (255, 255, 255)
GREEN = (0, 128, 0)
RED = (200, 0, 0)
BLACK = (0, 0, 0)
GREENER = (0, 255, 0)

# Load images
deathstar_image = pygame.image.load("deathstar2.png")
player_image = pygame.image.load("xwingss.png")
player_image = pygame.transform.scale(player_image, (35, 35))
enemy_image = pygame.image.load("tiefighter.png")
enemy_image = pygame.transform.scale(enemy_image, (35, 35))
mouse_image = pygame.image.load("mouseicon.png")

# Game settings
PLAYER_SPEED = 5
PLAYER_HEALTH = 100
PLAYER_LIVES = 3
PROJECTILE_SPEED = 7
ENEMY_SPEED = 4
FIRE_RATE = 250  # milliseconds

# Initialize variables
player_x, player_y = WIDTH // 2, HEIGHT // 4
player_health = PLAYER_HEALTH
player_lives = PLAYER_LIVES
kills = 0
is_firing = False
last_shot_time = pygame.time.get_ticks()

projectiles = []
enemy_projectiles = []
enemies = []
stars = [(random.randint(0, WIDTH), random.randint(0, HEIGHT)) for _ in range(400)]

def rotate_player_towards_mouse():
    """Rotate the player's image so the bottom points towards the mouse."""
    global player_image_rotated, player_x, player_y
    mouse_x, mouse_y = pygame.mouse.get_pos()
    
    # Calculate the angle between the player and the mouse
    delta_x = mouse_x - (player_x + 17)  # Center of the player
    delta_y = mouse_y - (player_y + 17)
    angle = math.degrees(math.atan2(-delta_y, delta_x)) + 90  # Rotate to point the bottom
    
    # Rotate the player image
    player_image_rotated = pygame.transform.rotate(player_image, angle)
    
    # Update the new rect to center the rotated image correctly
    player_rect = player_image_rotated.get_rect(center=(player_x + 17, player_y + 17))
    return player_rect

def reset_player():
    """Reset player position and health for respawn."""
    global player_x, player_y, player_health
    player_x, player_y = WIDTH // 2, HEIGHT // 4
    player_health = PLAYER_HEALTH

def shoot_projectile():
    """Create a projectile shooting from the player towards the mouse."""
    laser_channel.play(laser_sound)
    mouse_x, mouse_y = pygame.mouse.get_pos()
    delta_x = mouse_x - (player_x + 17)
    delta_y = mouse_y - (player_y + 17)
    angle = math.atan2(delta_y, delta_x)
    projectiles.append({
        'x': player_x + 17,
        'y': player_y + 17,
        'dir_x': math.cos(angle),
        'dir_y': math.sin(angle),
        'speed': PROJECTILE_SPEED
    })

def spawn_enemies(num_enemies):
    """Spawn enemies at random locations off-screen."""
    for _ in range(num_enemies):
        # Randomly choose if the enemy spawns from left, right, top, or bottom
        side = random.choice(['left', 'right', 'top', 'bottom'])
        
        if side == 'left':
            x = random.randint(-100, -35)  # Off-screen to the left
            y = random.randint(0, HEIGHT)  # Anywhere vertically within the screen
        elif side == 'right':
            x = random.randint(WIDTH, WIDTH + 100)  # Off-screen to the right
            y = random.randint(0, HEIGHT)  # Anywhere vertically within the screen
        elif side == 'top':
            x = random.randint(0, WIDTH)  # Anywhere horizontally within the screen
            y = random.randint(-100, -35)  # Off-screen to the top
        elif side == 'bottom':
            x = random.randint(0, WIDTH)  # Anywhere horizontally within the screen
            y = random.randint(HEIGHT, HEIGHT + 100)  # Off-screen to the bottom

        enemy_channel = enemy_channels[len(enemies) % len(enemy_channels)]
        enemies.append({'x': x, 'y': y, 'health': 100, 'channel' : enemy_channel})

def get_direction_vector(from_x, from_y, to_x, to_y):
    """Calculate normalized direction vector from one point to another."""
    delta_x = to_x - from_x
    delta_y = to_y - from_y
    distance = math.hypot(delta_x, delta_y)
    return (delta_x / distance, delta_y / distance) if distance != 0 else (0, 0)

def avoid_collision(enemy, distance=50):
    """Adjust the enemy's position to avoid collisions with other enemies."""
    for other in enemies:
        if other == enemy:
            continue
        dx = enemy['x'] - other['x']
        dy = enemy['y'] - other['y']
        dist = math.hypot(dx, dy)
        if dist < distance:
            move_distance = distance - dist
            if dist != 0:
                move_x = (dx / dist) * move_distance
                move_y = (dy / dist) * move_distance
                enemy['x'] += move_x
                enemy['y'] += move_y

def reset_game():
    """Reset all game variables to start a new game."""
    global player_lives, player_health, enemies, projectiles, enemy_projectiles, kills
    player_lives = PLAYER_LIVES
    kills = 0
    enemies.clear()
    projectiles.clear()
    enemy_projectiles.clear()
    reset_player()
    spawn_enemies(1)

def handle_collision():
    """Handle collisions between the player and enemies."""
    global player_x, player_y, player_health, player_lives
    for enemy in enemies[:]:
        if (player_x < enemy['x'] + 35 and
            player_x + 35 > enemy['x'] and
            player_y < enemy['y'] + 35 and
            player_y + 35 > enemy['y']):
            player_health -= 10
            if player_health <= 0:
                player_lives -= 1
                if player_lives == 0:
                    return False
                reset_player()
            dir_x, dir_y = get_direction_vector(player_x, player_y, enemy['x'], enemy['y'])
            player_x -= dir_x * PLAYER_SPEED * 5
            player_y -= dir_y * PLAYER_SPEED * 5
    return True

def draw_health_bar(surface, x, y, health, max_health):
    """Draw a health bar at the given position."""
    bar_width = 50
    bar_height = 10
    pygame.draw.rect(surface, BLACK, (x - bar_width // 2, y - bar_height - 5, bar_width, bar_height))
    pygame.draw.rect(surface, GREENER, (x - bar_width // 2, y - bar_height - 5, (health / max_health) * bar_width, bar_height))

# Game loop
clock = pygame.time.Clock()
running = True
paused = False
enemies_to_spawn = 1
spawn_enemies(enemies_to_spawn)

while running:

    keys = pygame.key.get_pressed()
    current_time = pygame.time.get_ticks()

    if keys[pygame.K_ESCAPE]:
        running = False

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:
            is_firing = True
        elif event.type == pygame.MOUSEBUTTONUP and event.button == 1:
            is_firing = False

    if is_firing and current_time - last_shot_time > FIRE_RATE:
        shoot_projectile()
        last_shot_time = current_time

    if not paused:
        # Player movement
        if keys[pygame.K_a]: player_x -= PLAYER_SPEED
        if keys[pygame.K_d]: player_x += PLAYER_SPEED
        if keys[pygame.K_w]: player_y -= PLAYER_SPEED
        if keys[pygame.K_s]: player_y += PLAYER_SPEED

        # Player boundaries
        player_x = max(0, min(WIDTH - 35, player_x))
        player_y = max(0, min(HEIGHT - 35, player_y))

        # Enemy movement and actions
        for enemy in enemies:
            dir_x, dir_y = get_direction_vector(enemy['x'], enemy['y'], player_x, player_y)
            enemy['x'] += dir_x * ENEMY_SPEED
            enemy['y'] += dir_y * ENEMY_SPEED
            avoid_collision(enemy)

            if random.randint(1, 100) > 98:
                enemy['channel'].play(tie_laser_sound)
                angle = math.atan2(player_y - enemy['y'], player_x - enemy['x'])
                enemy_projectiles.append({
                    'x': enemy['x'] + 17,
                    'y': enemy['y'] + 17,
                    'dir_x': math.cos(angle),
                    'dir_y': math.sin(angle),
                    'speed': PROJECTILE_SPEED
                })

        # Move and remove projectiles
        for projectile in projectiles[:]:
            projectile['x'] += projectile['dir_x'] * projectile['speed']
            projectile['y'] += projectile['dir_y'] * projectile['speed']
            if projectile['x'] < 0 or projectile['x'] > WIDTH or projectile['y'] < 0 or projectile['y'] > HEIGHT:
                projectiles.remove(projectile)

        for enemy_projectile in enemy_projectiles[:]:
            enemy_projectile['x'] += enemy_projectile['dir_x'] * enemy_projectile['speed']
            enemy_projectile['y'] += enemy_projectile['dir_y'] * enemy_projectile['speed']

            if (player_x < enemy_projectile['x'] < player_x + 35 and
                player_y < enemy_projectile['y'] < player_y + 35):
                enemy_projectiles.remove(enemy_projectile)
                player_health -= 10
                if player_health <= 0:
                    if player_lives > 0:
                        player_lives -= 1
                        reset_player()
                    if player_lives <= 0:
                        paused = True
                break

            if (enemy_projectile['x'] < 0 or enemy_projectile['x'] > WIDTH or
                enemy_projectile['y'] < 0 or enemy_projectile['y'] > HEIGHT):
                enemy_projectiles.remove(enemy_projectile)

        # Projectile collision with enemies
        for projectile in projectiles[:]:
            for enemy in enemies[:]:
                if (enemy['x'] < projectile['x'] < enemy['x'] + 35 and
                    enemy['y'] < projectile['y'] < enemy['y'] + 35):
                    projectiles.remove(projectile)
                    enemy['health'] -= 10
                    if enemy['health'] <= 0:
                        enemies.remove(enemy)
                        kills += 1
                        if not enemies:
                            enemies_to_spawn += 1
                            spawn_enemies(enemies_to_spawn)
                    break

        if not handle_collision():
            paused = True

    # Drawing
    screen.fill(BLACK)
    for star in stars:
        pygame.draw.circle(screen, WHITE, star, 2)

    screen.blit(deathstar_image, (WIDTH / 2 - 200, HEIGHT / 2 - 200))

    if not paused:
        player_rect = rotate_player_towards_mouse()
        screen.blit(player_image_rotated, player_rect.topleft)
        draw_health_bar(screen, player_x + 35 // 2, player_y, player_health, PLAYER_HEALTH)
        for enemy in enemies:
            screen.blit(enemy_image, (enemy['x'], enemy['y']))
            draw_health_bar(screen, enemy['x'] + 35 // 2, enemy['y'], enemy['health'], 100)
        for projectile in projectiles:
            pygame.draw.rect(screen, RED, (projectile['x'], projectile['y'], 10, 10))
        for enemy_projectile in enemy_projectiles:
            pygame.draw.rect(screen, GREENER, (enemy_projectile['x'], enemy_projectile['y'], 10, 10))
        for i in range(player_lives):
            pygame.draw.rect(screen, GREEN, (10 + i * (25 + 5), 10, 20, 20))
    else:
        music.fadeout(3000)
        font = pygame.font.SysFont(None, 45)
        game_over_text = font.render(f'YOU DIED - YOU KILLED {kills} TIE FIGHTERS', True, RED)
        screen.blit(game_over_text, game_over_text.get_rect(center=(WIDTH // 2, HEIGHT // 4)))
        restart_text = font.render('SPACE TO RESTART', True, RED)
        screen.blit(restart_text, restart_text.get_rect(center=(WIDTH // 2, HEIGHT // 4 + 50)))
        if keys[pygame.K_SPACE]:
            reset_game()
            paused = False
        if keys[pygame.K_ESCAPE]:
            running = False

    mouse_x, mouse_y = pygame.mouse.get_pos()
    screen.blit(mouse_image, (mouse_x, mouse_y))

    pygame.display.flip()
    clock.tick(60)

pygame.quit()
