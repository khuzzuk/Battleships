package gameInterface.Listeners;

import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShootListener extends AbstractAction {
    private Point boardCoordinates;
    private Game game;
    private Player player;

    public ShootListener(Point boardCoordinates, Game game, Player player) {
        this.boardCoordinates = boardCoordinates;
        this.game = game;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.shootOnPlayerBoard(boardCoordinates,player);
    }
}
