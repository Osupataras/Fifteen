package com.taras.config;

import java.io.File;

/**
 * Created by Taras on 13.10.2016.
 */
public interface IGameMenu {
    void setNewGame(int gameMatrix[][]);
    String setSaveGame();
    File getSavedGame();
    String setSavedGamePath();
}
