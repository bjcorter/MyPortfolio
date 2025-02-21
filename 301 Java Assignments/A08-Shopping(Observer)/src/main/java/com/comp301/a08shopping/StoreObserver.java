package com.comp301.a08shopping;

import com.comp301.a08shopping.events.StoreEvent;

public interface StoreObserver {
  void update(StoreEvent event);
}
