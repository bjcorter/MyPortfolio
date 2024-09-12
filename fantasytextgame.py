"""Medieval text based game by Ben Corter"""
import random
WHITE_BOX: str = "\U00002B1C"


player: str = ""
points: int


def main() -> None:
    """Main function of the game."""
    global player
    global points
    global archetype
    greet()
    step1()
    if archetype == "3":
        peasant()
    if archetype == "1":
        knight()
        riddle(points)
    if archetype == "2":
        wizard()
        riddle(points)
    ending()
   
    
def greet() -> None:
    """Greeting of the game."""
    global points
    points = int(10)
    global player
    player = str(input(f"{WHITE_BOX} 'Today, my friend, is the beginning of your new life. The following days, weeks, or even years hang in the balance, all dependent on how you choose to move forward. As a humble peasant of these lands, you have no where to move but up, however, I warn you, the path to glory shall be a treacherous one. Only the strong survive, and you must prove you have what it takes to be remembered as a hero, a legend, or dare I say, a god. I, Lord Percival of The Rolling Hills, as a member of the roundtable, am here to foresee your journey and judge if you are worthy of timeless glory, or if you are to be left to rot away in the fields of your master. There will come a time, quite possibly numerous times, in which your morals, strength, integrity and heart will be put to the test. Before I send you on your path, be it to the highest of highs or the lowest of lows, I must know what it is you call yourself.' *Type your name and press enter* "))
    input(f"{WHITE_BOX} 'Ah, {player}, how interesting! As it so happens, that is the name of our jester. If you share other similarities with that buffoon, I hold great reservations about your success, however, best of luck be to you. On your way now.' *Press Enter to continue*")


def step1() -> str:
    """Establishes players choice of archetype."""
    global player
    global archetype
    input(f"{WHITE_BOX} 'You return to your shack on the farm of your Lord, walking whilst daydreaming images of grandeur, fair maidens smitten by your mere prescence, a sea of gold before you so bringht you must squint to behold it, and the thorny crown of mighty King Cedric bestowed upon your head. You arrive home and hope to sleep the night away, and it isn't until then that nightmares stemming from doubt and fear of failure creep into your mind, dark images of everlasting poverty, hunger, death, and worst of all, shame. You toss and turn and get little rest, however, eventually you fall asleep. Hours later, you awake in a sweat and begin your journey.'*Press Enter*")
    valid_archetypes: list = ["1", "2", "3"]
    archetype = str(input(f"{WHITE_BOX} 'As you, {player}, start your day, you begin to ponder what you hope to achieve in your new life. The ideas that come to mind are, 1. A fearless warrior of the Knights roundtable, 2. A wizard capable of magic to manipulate the world around them, 3. Continue on with the simple lifestyle of a humble peasant.' *Enter 1, 2, or 3 to choose your path*"))
    while archetype not in valid_archetypes:
        archetype = str(input(f"{WHITE_BOX} 'Please enter the number, 1 through 3, that corresponds with the class you wish to proceed with.'"))
    return archetype


def peasant() -> None:
    """Defines the peasant route."""
    global archetype
    input(f"{WHITE_BOX} 'The status of your glory equates to {points}. You live a long, simple life, and although you lived an overall happy life, next to no one will remember the name {player}, unless they are referring to the lovely yet daft jester.'*Enter*")
    archetype = input(f"{WHITE_BOX} 'You have chosen the life of a peasant, however, the Gods see great things to come in your future shall you choose another path. If you would like to change course, enter 1 to become a knight and enter 2 to become a wizard, or enter 3 to remain a peasant. *Enter 1, 2, or 3*")
        

def riddle(number: int) -> int:
    """Riddle int function, uses randomness and edits points, returns int."""
    input(f"{WHITE_BOX} 'Much later, possibly years, you are approached by a strange man in a robe. You can hardly see his face.'*Enter*")
    global points
    global player
    global archetype
    gamble: str = input(f"{WHITE_BOX} 'Weary traveler, I, Keeper of the Guild of Prosperity, am here to give you an opportunity. This opportunity may make or break your legacy, yet I have great confidence in your abilities, as should you. The current status of your glory equates to {points}, and if you're willing to risk your glory, the upsides are potentially massive and could cement you as a legend. So, what do you say? Do you want to gamble your glory?' *Type yes or no and press Enter*")
    valid_gamble: list = ["yes", "no"]
    while gamble not in valid_gamble:
        gamble = str(input(f"{WHITE_BOX} 'Please enter yes or no'"))
    while gamble == "no":
        gamble = input(f"{WHITE_BOX} 'That's a shame. I was looking foward to some excitement. You know, I haven't encountered a traveler such as yourself in decades. It gets awfully lonely. Would you reconsider?'*Type yes or no and press enter* ")
        gamble = input(f"{WHITE_BOX} 'Ok come on. Now I'm making you. You had a choice, but now it's my choice. We're doing it, ok?'*Type yes or no and press enter*")
        input(f"{WHITE_BOX} 'Woah that's crazy, you said yes. Lets get started'*Enter*")
        gamble = "yes"
    if gamble == "yes":
        num = int(input(f"{WHITE_BOX} 'Ok, {player}, how many of your {points} points would you like to risk? You may lose them, but you also may win many more.'*Enter a number of points less than or equal to the points you have*"))
        input(f"{WHITE_BOX} ' Alright,  {num} points it is. On to the challenge.' *Enter*")
        ans: int = random.randint(1,10)
        guess: int = input(f"{WHITE_BOX} 'I hope lady luck is on your side for this one. I'm thinking of number between one and ten. What number is it? *Guess a number from 1-10*")
        attempt: int = 0
        while guess != ans and attempt < 1:
            guess = int(input(f"{WHITE_BOX} 'Not quite, I'll give you one more go at it.' *Enter another guess*"))
            attempt += 1
        if guess == ans:
            input(f"{WHITE_BOX} 'By the power of Zues, you got it! I'll award you {int(num) * 2} glory!'")
            num = points
        else:
            input(f"{WHITE_BOX} 'Ok, thats enough. You've lost your glory. I'll give you a chance to earn it back. This time, it won't be up to luck, but if you lose, you'll be renamed {player}, King of Buffoons and Imbiciles. You ready? Of course you are, so lets just get right into it.'*Enter* ")
            guess2: int = input(f"{WHITE_BOX} 'So, here's a riddle. What has a head, a tail, but no body?' *guess one word*")
            if guess2 == "coin":
                input(f"{WHITE_BOX} 'That's amazing! You've certainly earned your glory back, and I'll even award you some more. You now have {int(points) + 25} glory. Well Done! *Enter*")
                num = 25
            elif guess2 != "coin":
                input(f"{WHITE_BOX} 'I hereby crown you King of Buffoons and Imbiciles. Henceforth, you shall be known by this name and your glory is now 0.'*Enter*")
                player = f"{player}, King of Buffoons and Imbiciles"
                points = 0
                num = 0
        points += num    
    return points
 
               
def knight() -> None:
    """Defines the Knight route."""
    global points
    input(f"{WHITE_BOX} 'To pursue your future of becoming a noble Knight of King Cedrics Roundtable, you begin to train. After long hours in the fields, everyday you return to your shack and can be seen striking a dummy made from hay and a burlap sack with a wooden sword. After months of doing so, someone has taken a keen eye to your dedication and potential as a warrior.' *Enter*")
    input(f"{WHITE_BOX} 'You are called before the Roundtable''Hello, {player}, we have taken notice of your dedication to becoming a Knight, and we wish to grant you an opportuntiy. If you are successful, a seat at the table next to us will be your prize. Are you ready to hear your challenge? *Enter*")
    choice: str = input(f"{WHITE_BOX} 'There lives a troll over beyond the edge of the Rolling Hills we fear may attack. Will you slay the beast?' *Enter yes or no*")
    valid_choice: list = ["yes", "no"]
    while choice not in valid_choice:
        choice = str(input(f"{WHITE_BOX} 'Please enter yes or no'"))
    if choice == "yes":
        input(f"{WHITE_BOX} 'Then be off with you, and return when the deed is done.*Enter*")
        input(f"{WHITE_BOX} 'You travel to the lair of the beast, filled with a mix of fear and excitement. As you enter the lair, you see the troll. You raise your sword, and the troll begins to speak.'*Enter*")
        choice2: str = input(f"{WHITE_BOX} 'Oh, have they sent you to kill me!? Please don't! I've lived in this cave for over 300 years and never disturbed a single person. Please, I beg of you, let me be.'*Enter kill to kill the troll, or enter spare to spare the troll.*")
        valid_choice2: list = ["kill", "spare"]
        while choice2 not in valid_choice2:
            choice2 = str(input(f"{WHITE_BOX} 'Please enter kill or spare'"))
        if choice2 == "kill":
            input(f"{WHITE_BOX} 'You chose to kill the defenseless troll. You explain the situation before the Roundtable and they are disgusted by your actions, for the table knew of the troll and was testing your moral code. The troll was of no threat and had conversed with the table previously about your test. Your glory remains at 10 and you are exiled from living and working in the Rolling Hills.*Enter*")
        elif choice2 == "spare":
            input(f"{WHITE_BOX} 'You chose to spare the troll, and for good reason. The roundtable talked with the troll previous to your assignment and they were testing your morality, for if you had killed the defenseless troll, you would have been exiled. The council is pleased with your choice and grant you a spot as a prestigious knight. Your glory has risen to 100 *Enter*")
            points += 90
    elif choice == "no":
        input(f"{WHITE_BOX} 'Your cowardice is apparent to the Roundtable, and your glory is reduced to 5.*Enter*")
        points -= 5
            

def wizard() -> None:
    """Defines the wizard route."""
    global points
    input(f"{WHITE_BOX} 'To pursue your future of becoming a powerful wizard, you purchase a spell book from a local wizard. Each day after working long in the fields for your lord, you sit in your shack and practice pronouncing spells and wand movements. Over several months, you reach the end of the book, and return to the wizard who sold you the book, desperate for more knowledge.'*Enter*")
    input(f"{WHITE_BOX} 'You speak to the wizard and beg for another book, another spell, anything to progress your magic abilities and become a legendary wizard. The old man says he doesn't have another book, but he knows where one might be. He explains the path is treacherous and to avoid the book, for its magic has corrupted all those who previously read from its tattered pages. The book is cursed, he says, and once a word of the magic within its binds is spoken, your soul will too be cursed.'*Enter*")
    choice: str = input(f"{WHITE_BOX} 'The power in that book is limitless, yet evil. You are faced with a decision.'*Enter yes to pursue the book, enter no to avoid the book.")
    valid_choice: list = ["yes", "no"]
    while choice not in valid_choice:
        choice = str(input(f"{WHITE_BOX} 'Please enter yes to get the book or no to leave the book'"))
    if choice == "yes":
        input(f"{WHITE_BOX} 'You have chosen to pursue the ancient book of powerful magic. You travel days across the Rolling Hills and reach the desolate mountain path at which the book is sitting atop. As you climb, you convince yourself you aren't like those wizards of the past, you cannot be corrupted and you shall use the book for the good of humanity.*Enter*")
        choice2: str = input(f"{WHITE_BOX} 'You reach the summit and gaze upon the book seated on a pedestal. As you reach for it, something in your mind causes you to hesitate. You can feel the negative energy of the book drawing you in.'*Enter grab to grab the book or leave to return home*")
        valid_choice2: list = ["grab", "leave"]
        while choice2 not in valid_choice2:
            choice2 = str(input(f"{WHITE_BOX} 'Please enter grab to take the book are leave to leave it'"))
        if choice2 == "grab":
            input(f"`{WHITE_BOX} 'As soon as your hand touches the cover of the book, dark magical orbs whirl around you, sounds of women and children screaming fill your ears. You are intensely frightened, then slowly an evil grin wipes across your face. You feel urged to unleash a powerful scream, and upon doing so, those dark orbs of hateful magic spread across the sky and toward the Rolling Hills. They attack the fair people of the land, spreading disease, kiling crops, and starting fires. The wizard tells the town that you are responsible before falling prey to the magic.'*Enter*")
            input(f"{WHITE_BOX} 'Your glory falls to 0, and everyone is fearful of your corrupted magic. Soon, you are hunted and nearly slain by the Knights of King Cedrics Roundtable. You hardly escape with your life and flee to a far away land as a crippled hermit for the remainder of your days.'*Enter*")
            points -= 10
        elif choice2 == "leave":
            input(f"{WHITE_BOX} 'You have chosen to leave the corrupted book where its sits, despite its power calling to you. As you leave on the desolate path down the mountain, you feel a warm energy wash over you. You snap your fingers and lush trees burst into life, birds begin to chirp, and a rainbow forms above your head. The people of the Rolling Hills are as happy as ever, and you have brought peace and prosperity to the land by leaving the dark ancient magic untouched. Your glory improves to 100 and you will go down in history as a true noble wizard.*Enter*")
            points += 90
    elif choice == "no":
        input(f"{WHITE_BOX} 'You have chosen to leave the corrupted book where its sits and not pursue it, despite knowing of its great power. As you leave the wizard and venture home, you feel a warm energy wash over you. You snap your fingers and lush trees burst into life, birds begin to chirp, and a rainbow forms above your head. The people of the Rolling Hills are as happy as ever, and you have brought peace and prosperity to the land by leaving the dark ancient magic untoucheed. Your glory improves to 100 and you will go down in history as a true noble wizard.*Enter*")
        points += 90


def ending() -> None:
    """Defines the ending of the game."""
    global points
    global archetype
    global player
    arch_name: str = ""
    glory: str
    if points > 25:
        glory = "Worthy"
    else:
        glory = "Unworthy"
    if archetype == "1":
        arch_name = "Knight"
    if archetype == "2":
        arch_name = "Wizard"
    if archetype == "3":
        arch_name == "Peasant"
    input(f"{WHITE_BOX} 'Throughout your life, {player}, as a {arch_name}, you gained a glory score of {points}, making you {glory} of timeless glory.")
    if points >= 100:
        input(f"{WHITE_BOX} 'Due to your high honor, King Cedric wishes to speak with you.'*Enter*")
        input(f"{WHITE_BOX} 'Ah, if isn't {player}! You've been the talk of the town, and the great people of the Rolling Hills look up to, they aspire to be like you. As you see, I'm growing older by the day. This place needs a strong leader, one who shows bravery, intellect, and dedication. {player}, I hereby announce you to be The Royal Highness of the Rolling Hills.'*Enter*")
        input(f"{WHITE_BOX} 'King Cedric places his elegantly crafted crown upon your head, and hands you the keys to his vast and magestic castle. Your dreams have come true, and you live a long, happy life as a legendary ruler of the Rolling Hills'*Enter*")    

     
if __name__ == "__main__":
    main()