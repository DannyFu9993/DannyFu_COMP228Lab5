package com.example.dannyfu_comp228lab5;

public class PlayerAndGame {
    public Integer playerGameID;
    public Integer gameID;
    public Integer playerID;
    public String playingDate;
    public Integer score;

    public PlayerAndGame(Integer playerGameID, Integer gameID, Integer playerID, String playingDate, Integer score) {
        this.playerGameID = playerGameID;
        this.gameID = gameID;
        this.playerID = playerID;
        this.playingDate = playingDate;
        this.score = score;
    }

    public Integer getPlayerGameID() { return playerGameID; }
    public Integer getGameID() { return gameID; }
    public Integer getPlayerID() { return playerID; }
    public String getPlayingDate() { return playingDate; }
    public Integer getScore() { return score; }
}