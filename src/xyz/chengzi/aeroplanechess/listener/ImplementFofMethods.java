package xyz.chengzi.aeroplanechess.listener;

import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.ChessLocation;
import xyz.chengzi.aeroplanechess.model.ChessPiece;
import xyz.chengzi.aeroplanechess.model.StackPiece;
import xyz.chengzi.aeroplanechess.view.ButtonStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.List;

public class ImplementFofMethods implements MethodsForPlaying {

  public int Stack;
  private int NumberOfSecondRoll = 0;

  public int getNumberOfSecondRoll() {
    return NumberOfSecondRoll;
  }

  @Override
  public boolean CheckForGoOut(int numberOfDiceOne, int numberOfDiceTwo) {
    if (numberOfDiceOne == 6 || numberOfDiceTwo == 6) {
      return true;
    }
    return false;
  }


  @Override
  public int[] NumberOfMove(int numberOfDiceOne, int numberOfDiceTwo) {
    int add, sub = 0, mul = 0, div = 0;
    add = numberOfDiceOne + numberOfDiceTwo;
    if (numberOfDiceOne != numberOfDiceTwo) {
      sub = Math.abs(numberOfDiceOne - numberOfDiceTwo);
    }
    if (numberOfDiceOne * numberOfDiceTwo <= 12) {
      mul = numberOfDiceOne * numberOfDiceTwo;
    }
    if (numberOfDiceOne >= numberOfDiceTwo && numberOfDiceOne % numberOfDiceTwo == 0) {
      div = numberOfDiceOne / numberOfDiceTwo;
    }
    if (numberOfDiceTwo >= numberOfDiceOne && numberOfDiceTwo % numberOfDiceOne == 0) {
      div = numberOfDiceTwo / numberOfDiceOne;
    }
    int[] num = {add, sub, mul, div};
    return num;
  }

  @Override
  public boolean EatOthersPiece(ChessPiece piece1, ChessPiece piece2, ChessBoard board,
                                ChessBoardLocation location1, ChessBoardLocation location2) {
    if (CheckAnyPlayer(piece1, board, location2)) {
      if (board.getGridAt(location2).getPiece() == piece2) {
        if (piece1.getPlayer() != piece2.getPlayer()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean CheckAnyPlayer(ChessPiece piece, ChessBoard board, ChessBoardLocation location) {
    if (board.getGridAt(location).getPiece() == null) {
      return false;
    }
    if (board.getGridAt(location).getPiece() == piece) {
      System.out.println("CheckAnyPlayer: " + piece + " ; " + board.getGridAt(location).getPiece());
      return false;
    }
    return true;
  }


  @Override
  public boolean BonusLocation(ChessBoardLocation location, ChessPiece chessPiece) {
    if (location.getColor() == chessPiece.getPlayer()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean anotherRoll(int numberOfDiceTwo, int numberOfDiceOne) {
    if (numberOfDiceOne + numberOfDiceTwo >= 10) {
      NumberOfSecondRoll++;
      return true;
    }
    NumberOfSecondRoll = 0;
    return false;
  }

  @Override
  public boolean TooLuckyTooUnlucky(int player, int NumberOfDiceOne, int NumberOfDiceTwo) {
    if (NumberOfSecondRoll >= 3) {
      if (anotherRoll(NumberOfDiceTwo, NumberOfDiceOne)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int ChooseToStack(ChessPiece chessPiece1, ChessPiece chessPiece2, ChessBoard board,
                           ChessBoardLocation location2) {
    if (chessPiece1.getPlayer() == chessPiece2.getPlayer()) {
      JFrame jf = new JFrame("是否重叠？");
      jf.setSize(200, 200);
      jf.setLocationRelativeTo(null);
      jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      JPanel panel = new JPanel();

      // Create 2 radio buttons
      JRadioButton radioBtn01 = new JRadioButton("是");
      JRadioButton radioBtn02 = new JRadioButton("否");

      // Create a button group and add two radio buttons to the group
      ButtonGroup btnGroup = new ButtonGroup();
      btnGroup.add(radioBtn01);
      btnGroup.add(radioBtn02);

      // Set the first radio button to be selected
      radioBtn01.setSelected(false);
      radioBtn02.setSelected(false);

      panel.add(radioBtn01);
      panel.add(radioBtn02);

      JButton button = new JButton("确定");
      panel.add(button);

      jf.add(panel);
      jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      jf.setVisible(true);

      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String info = "";
          //Get all the components on the panel through the panel property name
          System.out.println(info);
          for (Component c : panel.getComponents()) {
            if (c instanceof JRadioButton) {
              if (((JRadioButton) c).isSelected()) {
                info += ((JRadioButton) c).getText();
              }
            }
          }
          System.out.println(info);

          StackPiece stackPiece;
          if (chessPiece2 instanceof StackPiece){
            stackPiece = (StackPiece) chessPiece2;
          }else{
            stackPiece = new StackPiece(chessPiece2.getPlayer(),chessPiece2.getNumber());
          }
          if (info.equals("是")) {
            Stack = 2;
            if (chessPiece1 instanceof StackPiece){
              stackPiece.addPiece(((StackPiece) chessPiece1).getStackPieceNums());
              stackPiece.addPiece(chessPiece1);
            }else {
              stackPiece.addPiece(chessPiece1);
            }
          } else {
            Stack = 1;
            stackPiece.guestPiece(chessPiece1);
          }
          JOptionPane.showMessageDialog(null, "你选择了" + info);
          System.out.println("stack:" + Stack);
          board.setChessPieceAt(location2, stackPiece);
          System.out.println("check grid:"+board.getChessPieceAt(location2));
        }
      });

      return Stack;
    }

    return -1;
  }


  @Override
  public void CompeteForEatingPiece(int DiceOne, int DiceTwo) {

  }
}
