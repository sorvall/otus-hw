package ru.sorokin.listener;

import ru.sorokin.model.Message;

public class ListenerHistory implements Listener {
    private final HistoryList historyList;

    public ListenerHistory(HistoryList historyList) {
        this.historyList = historyList;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        System.out.println("ListenerHistory Run");
        historyList.add(oldMsg, newMsg);
    }
}
