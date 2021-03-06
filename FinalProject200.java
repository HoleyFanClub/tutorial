import java.io.*;

// Color
import java.awt.*;

// MouseEvent
import java.awt.event.*;

// JLabel, JPanel
import javax.swing.*;

// GRect, GLabel
import acm.graphics.*;

// GraphicsProgram
import acm.program.*;
//file, file not found exception
import java.io.*;
import java.util.*;
import java.lang.*;



//@author Thomas Torbert @programmer Matt Rotter
public class FinalProject200 implements ActionListener
{
  //Constructs instance variables
  JFrame[] frame;
  JPanel[] tiles;
  JPanel[] topWall;
  JPanel[] leftWall;
  JPanel[] bottomWall;
  JPanel[] rightWall;
  JButton[] floors, doors, walls;
  JButton[] fb0, fb1, fb2, fb3, fb4, fb5, fb6, fb7, fb8;
  int[] buttonType;
  int[] specialFloors;
  JButton[] unusableFloors;
  int keys;
  boolean used0, used1, used2, used3, used4, used5, used6;
  TimerTest myTimer;
  int seconds = 0;
  java.util.Timer timer;
  TimerTask timerTask;
  int clockUsed = 0;
  int keysConsumed = 0;
  JFrame startingLocation;
  JFrame trapRoom;
  JButton[] startingRoomButtons;
  JButton[] trapRoomButtons;
  JPanel[] trapandstarting;
  JPanel[] trapandstarting2;
  JPanel trapFloor;
  JPanel startFloor;
  JButton[] generalLocation, properties, specificLocation;
  int difficulty = 30;
  boolean canClick = true;
  
  public static void main(String[] args)
  {
    
    new FinalProject200();
    
  }
  public FinalProject200()
  {
    //gets difficulty from user
    String difficultyt = JOptionPane.showInputDialog("please enter a difficulty, easy, medium, or hard.");
    
    if(difficultyt.equals("easy"))
    {
      difficulty = 60;
    }
    else if(difficultyt.equals("medium"))
    {
      difficulty = 30;
    }
    else if(difficultyt.equals("hard"))
    {
      difficulty = 15;
    }
    else
    {
      System.out.println("you didnt select a difficulty, defaulting to medium");
    }
    //states the story
    System.out.println("you arrive outside the abandoned school.  Your buddy told you to come here but he had a bit of a strange look in his eye when he said it \n" +
                       "Never the less, hes a good friend of yours so you're sure something good must be here\n" +
                       "You get the feeling that once you enter this school, whatever is inside will require your full attention (the clock will start) \n");
    
    //lines 92 to 104 sets up the buttons used at the bottom of the frames
    generalLocation = new JButton[9];
    properties = new JButton[9];
    specificLocation = new JButton[9];
    
    for(int i = 0; i < 9; i++)
    {
      generalLocation[i] = new JButton("list all locations");
      properties[i] = new JButton("list the properties of the room");
      generalLocation[i].addActionListener(this);
      properties[i].addActionListener(this);
      specificLocation[i] = new JButton("list the connections from here");
      specificLocation[i].addActionListener(this);
    }
    //lines 106 to 182 sets up the starting location and the trap room
    trapandstarting = new JPanel[4];
    trapandstarting2 = new JPanel[4];
    trapFloor = new JPanel(new GridLayout(1,1));
    startFloor = new JPanel(new GridLayout(1,1));
    
    for(int i = 0; i < 4; i++)
    {
      trapandstarting[i] = new JPanel(new GridLayout(1 , 3));
      trapandstarting2[i] = new JPanel(new GridLayout(3 , 1));
    }
    
    startingRoomButtons = new JButton[13];
    trapRoomButtons = new JButton[13];
    
    for(int i = 0; i < 12; i++)
    {
      startingRoomButtons[i] = new JButton();
      startingRoomButtons[i].setBackground(Color.lightGray);
      trapRoomButtons[i] = new JButton();
      trapRoomButtons[i].setBackground(Color.lightGray);
      
    }
    startingRoomButtons[12] = new JButton("front door");
    trapRoomButtons[12] = new JButton();
    startingRoomButtons[12].addActionListener(this);
    startingRoomButtons[12].setBackground(Color.lightGray);
    
    trapandstarting[0].add(startingRoomButtons[0]);
    trapandstarting[0].add(startingRoomButtons[1]);
    trapandstarting[0].add(startingRoomButtons[2]);
    trapandstarting[1].add(startingRoomButtons[3]);
    trapandstarting[1].add(startingRoomButtons[4]);
    trapandstarting[1].add(startingRoomButtons[5]);
    trapandstarting[2].add(startingRoomButtons[6]);
    trapandstarting[2].add(startingRoomButtons[7]);
    trapandstarting[2].add(startingRoomButtons[8]);
    trapandstarting[3].add(startingRoomButtons[9]);
    trapandstarting[3].add(startingRoomButtons[10]);
    trapandstarting[3].add(startingRoomButtons[11]);
    
    trapandstarting2[0].add(trapRoomButtons[0]);
    trapandstarting2[0].add(startingRoomButtons[12]);
    trapandstarting2[0].add(trapRoomButtons[2]);
    trapandstarting2[1].add(trapRoomButtons[3]);
    trapandstarting2[1].add(trapRoomButtons[4]);
    trapandstarting2[1].add(trapRoomButtons[5]);
    trapandstarting2[2].add(trapRoomButtons[6]);
    trapandstarting2[2].add(trapRoomButtons[7]);
    trapandstarting2[2].add(trapRoomButtons[8]);
    trapandstarting2[3].add(trapRoomButtons[9]);
    trapandstarting2[3].add(trapRoomButtons[10]);
    trapandstarting2[3].add(trapRoomButtons[11]);
    
    startFloor.add(trapRoomButtons[1]);
    trapFloor.add(trapRoomButtons[12]);
    
    
    
    startingLocation = new JFrame("outside front door");
    trapRoom = new JFrame("janitors closet");
    startingLocation.setSize(800, 650);
    trapRoom.setSize(800, 650);
    
    startingLocation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    trapRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    startingLocation.add(trapandstarting[0], BorderLayout.NORTH);
    startingLocation.add(trapandstarting[1], BorderLayout.SOUTH);
    startingLocation.add(trapandstarting2[0], BorderLayout.EAST);
    startingLocation.add(trapandstarting2[1], BorderLayout.WEST);
    startingLocation.add(startFloor, BorderLayout.CENTER);
    
    trapRoom.add(trapandstarting[2], BorderLayout.NORTH);
    trapRoom.add(trapandstarting[3], BorderLayout.SOUTH);
    trapRoom.add(trapandstarting2[2], BorderLayout.EAST);
    trapRoom.add(trapandstarting2[3], BorderLayout.WEST);
    trapRoom.add(trapFloor, BorderLayout.CENTER);
    
    //starts setting up the normal interior of the school.  lasts until line 888
    fb0 = new JButton[61];
    fb1 = new JButton[61];
    fb2 = new JButton[61];
    fb3 = new JButton[61];
    fb4 = new JButton[61];
    fb5 = new JButton[61];
    fb6 = new JButton[61];
    fb7 = new JButton[61];
    fb8 = new JButton[61];
    frame = new JFrame[9];
    tiles = new JPanel[9];
    topWall = new JPanel[9];
    leftWall = new JPanel[9];
    bottomWall = new JPanel[9];
    rightWall = new JPanel[9];
    walls = new JButton[83];
    doors = new JButton[25];
    floors = new JButton[441];
    buttonType = new int[549];
    specialFloors = new int[7];
    unusableFloors = new JButton[441];
    keys = 0;
    used0 = false;
    used1 = false;
    used2 = false;
    used3 = false;
    used4 = false;
    used5 = false;
    used6 = false;
    
    this.frame[0] = new JFrame("Starting Room, room 0");
    this.frame[1] = new JFrame("Abandoned teachers lounge, room 1");
    this.frame[2] = new JFrame("Classroom 1, room 2");
    this.frame[3] = new JFrame("Cafeteria, room 3");
    this.frame[4] = new JFrame("Courtyard W/Pit room 4");
    this.frame[5] = new JFrame("Classroom 2, room 5");
    this.frame[6] = new JFrame("Nurse's office, room 6");
    this.frame[7] = new JFrame("Front Entrance, room 7");
    this.frame[8] = new JFrame("Classroom 3, room 8");
    
    for(int i = 0; i < 9; i++)
    {
      
      
      this.frame[i].setSize(800, 650);
      this.frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      
      this.topWall[i] = new JPanel(new GridLayout(1,3));
      this.leftWall[i] = new JPanel(new GridLayout(3,1));
      this.bottomWall[i] = new JPanel(new GridLayout(2,3));
      this.rightWall[i] = new JPanel(new GridLayout(3,1));
      this.tiles[i] = new JPanel(new GridLayout(7,7));
      
      
      this.frame[i].add(this.topWall[i], BorderLayout.NORTH);
      this.frame[i].add(this.leftWall[i], BorderLayout.WEST);
      this.frame[i].add(this.rightWall[i], BorderLayout.EAST);
      this.frame[i].add(this.tiles[i], BorderLayout.CENTER);
      this.frame[i].add(this.bottomWall[i], BorderLayout.SOUTH);
    }
    
    
    
    
    for(int i = 0; i <= 548; i++)
    {
      if(i < 83)
      {
        walls[i] = new JButton("Wall");
        walls[i].setBackground(Color.gray);
        buttonType[i] = 2;
      }
      else if(i >= 83 && i < 108)
      {
        doors[i - 83] = new JButton("Door " + (i - 83));
        doors[i - 83].setBackground(Color.gray);
        buttonType[i] = 1;
      }
      else if(specialFloors[0] == i || specialFloors[1] == i || specialFloors[2] == i || specialFloors[3] == i || specialFloors[4] == i )
      {
        floors[i - 108] = new JButton(Integer.toString(i));
        floors[i - 108].setBackground(Color.gray);
        buttonType[i] = 3;
      }
      else
      {
        floors[i - 108] = new JButton(Integer.toString(i));
        floors[i - 108].setBackground(Color.gray);
        buttonType[i] = 0;
      }
    }
    
    for(int i = 0; i < 83; i++)
    {
      walls[i].addActionListener(this);
    }
    for(int i = 0; i < 25; i++)
    {
      doors[i].addActionListener(this);
    }
    for(int i = 0; i < 441; i++)
    {
      floors[i].addActionListener(this);
    }
    for(int i = 0; i <= 74; i++)
    {
      if(i == 0)
      {
        this.topWall[0].add(walls[0]);
      }
      else if(i == 1)
      {
        this.topWall[0].add(walls[1]);
      }
      else if(i == 2)
      {
        this.topWall[0].add(walls[2]);
      }
      else if(i == 3)
      {
        this.rightWall[0].add(walls[3]);
      }
      else if(i == 4)
      {
        this.rightWall[0].add(doors[0]);
      }
      else if(i == 5)
      {
        this.rightWall[0].add(walls[4]);
      }
      else if(i == 6)
      {
        this.bottomWall[0].add(walls[5]);
      }
      else if(i == 7)
      {
        this.bottomWall[0].add(doors[1]);
      }
      else if(i == 8)
      {
        this.bottomWall[0].add(walls[6]);
      }
      else if(i == 9)
      {
        this.leftWall[0].add(walls[7]);
      }
      else if(i == 10)
      {
        this.leftWall[0].add(walls[8]);
      }
      else if(i == 11)
      {
        this.leftWall[0].add(walls[9]);
      }
      
      else if(i == 12)
      {
        this.topWall[1].add(walls[10]);
      }
      else if(i == 13)
      {
        this.topWall[1].add(walls[11]);
      }
      else if(i == 14)
      {
        this.topWall[1].add(walls[12]);
      }
      else if(i == 15)
      {
        this.rightWall[1].add(walls[13]);
      }
      else if(i == 16)
      {
        this.rightWall[1].add(doors[2]);
      }
      else if(i == 17)
      {
        this.rightWall[1].add(walls[14]);
      }
      else if(i == 18)
      {
        this.bottomWall[1].add(walls[15]);
      }
      else if(i == 19)
      {
        this.bottomWall[1].add(doors[3]);
      }
      else if(i == 20)
      {
        this.bottomWall[1].add(walls[16]);
      }
      else if(i == 21)
      {
        this.leftWall[1].add(walls[17]);
      }
      else if(i == 22)
      {
        this.leftWall[1].add(doors[4]);
      }
      else if(i == 23)
      {
        this.leftWall[1].add(walls[18]);
      }
      else if(i == 24)
      {
        this.topWall[2].add(walls[19]);
      }
      else if(i == 25)
      {
        this.topWall[2].add(walls[20]);
      }
      else if(i == 26)
      {
        this.topWall[2].add(walls[21]);
      }
      else if(i == 27)
      {
        this.rightWall[2].add(walls[22]);
      }
      else if(i == 28)
      {
        this.rightWall[2].add(walls[23]);
      }
      else if(i == 29)
      {
        this.rightWall[2].add(walls[24]);
      }
      else if(i == 30)
      {
        this.bottomWall[2].add(walls[25]);
      }
      else if(i == 31)
      {
        this.bottomWall[2].add(doors[5]);
      }
      else if(i == 32)
      {
        this.bottomWall[2].add(walls[26]);
      }
      else if(i == 33)
      {
        this.leftWall[2].add(walls[27]);
      }
      else if(i == 34)
      {
        this.leftWall[2].add(doors[6]);
      }
      else if(i == 35)
      {
        this.leftWall[2].add(walls[28]);
      }
      else if(i == 36)
      {
        this.topWall[3].add(walls[29]);
      }
      else if(i == 37)
      {
        this.topWall[3].add(doors[7]);
      }
      else if(i == 38)
      {
        this.topWall[3].add(walls[30]);
      }
      else if(i == 39)
      {
        this.rightWall[3].add(walls[31]);
      }
      else if(i == 40)
      {
        this.rightWall[3].add(doors[8]);
      }
      else if(i == 41)
      {
        this.rightWall[3].add(walls[32]);
      }
      else if(i == 42)
      {
        this.bottomWall[3].add(walls[33]);
      }
      else if(i == 43)
      {
        this.bottomWall[3].add(doors[9]);
      }
      else if(i == 44)
      {
        this.bottomWall[3].add(walls[34]);
      }
      else if(i ==45)
      {
        this.leftWall[3].add(walls[35]);
      }
      else if(i == 46)
      {
        this.leftWall[3].add(walls[36]);
      }
      else if(i == 47)
      {
        this.leftWall[3].add(walls[37]);
      }
      else if(i == 48)
      {
        this.topWall[4].add(walls[38]);
      }
      else if(i == 49)
      {
        this.topWall[4].add(doors[10]);
      }
      else if(i == 50)
      {
        this.topWall[4].add(walls[39]);
      }
      else if(i == 51)
      {
        this.rightWall[4].add(walls[40]);
      }
      else if(i == 52)
      {
        this.rightWall[4].add(doors[11]);
      }
      else if(i == 53)
      {
        this.rightWall[4].add(walls[41]);
      }
      else if(i == 54)
      {
        this.bottomWall[4].add(walls[42]);
      }
      else if(i == 55)
      {
        this.bottomWall[4].add(doors[12]);
      }
      else if(i == 56)
      {
        this.bottomWall[4].add(walls[43]);
      }
      else if(i == 57)
      {
        this.leftWall[4].add(walls[44]);
      }
      else if(i == 58)
      {
        this.leftWall[4].add(doors[13]);
      }
      else if(i == 59)
      {
        this.leftWall[4].add(walls[45]);
      }
      else if(i == 60)
      {
        this.topWall[5].add(walls[46]);
      }
      else if(i == 61)
      {
        this.topWall[5].add(doors[14]);
      }
      else if(i == 62)
      {
        this.topWall[5].add(walls[47]);
      }
      else if(i == 63)
      {
        this.rightWall[5].add(walls[48]);
      }
      else if(i == 64)
      {
        this.rightWall[5].add(walls[49]);
      }
      else if(i == 65)
      {
        this.rightWall[5].add(walls[50]);
      }
      else if(i == 66)
      {
        this.bottomWall[5].add(walls[51]);
      }
      else if(i == 67)
      {
        this.bottomWall[5].add(doors[15]);
      }
      else if(i == 68)
      {
        this.bottomWall[5].add(walls[52]);
      }
      else if(i == 69)
      {
        this.leftWall[5].add(walls[53]);
      }
      else if(i == 70)
      {
        this.leftWall[5].add(doors[16]);
      }
      else if(i == 71)
      {
        this.leftWall[5].add(walls[54]);
      }
      else if(i == 72)
      {
        this.topWall[6].add(walls[55]);
      }
      else if(i == 73)
      {
        this.topWall[6].add(doors[17]);
      }
      else if(i == 74)
      {
        this.topWall[6].add(walls[56]);
      }
      
    }
    for(int i = 75; i < 549; i++)
    {
      if(i == 75)
      {
        this.rightWall[6].add(walls[57]);
      }
      else if(i == 76)
      {
        this.rightWall[6].add(doors[18]);
      }
      else if(i == 77)
      {
        this.rightWall[6].add(walls[58]);
      }
      else if(i == 78)
      {
        this.bottomWall[6].add(walls[59]);
      }
      else if(i == 79)
      {
        this.bottomWall[6].add(walls[60]);
      }
      else if(i == 80)
      {
        this.bottomWall[6].add(walls[61]);
      }
      else if(i == 81) 
      {
        this.leftWall[6].add(walls[62]);
      }
      else if(i == 82)
      {
        this.leftWall[6].add(walls[63]);
      }
      else if(i == 83)
      {
        this.leftWall[6].add(walls[64]);
      }
      else if(i == 84)
      {
        this.topWall[7].add(walls[65]);
      }
      else if(i == 85)
      {
        this.topWall[7].add(doors[19]);
      }
      else if(i == 86)
      {
        this.topWall[7].add(walls[66]);
      }
      else if(i == 87)
      {
        this.rightWall[7].add(walls[67]);
      }
      else if(i == 88)
      {
        this.rightWall[7].add(doors[20]);
      }
      else if(i == 89)
      {
        this.rightWall[7].add(walls[68]);
      }
      else if(i == 90)
      {
        this.bottomWall[7].add(walls[69]);
      }
      else if(i == 91)
      {
        this.bottomWall[7].add(doors[24]);
      }
      else if(i == 92)
      {
        this.bottomWall[7].add(walls[70]);
      }
      else if(i == 93)
      {
        this.leftWall[7].add(walls[71]);
      }
      else if(i == 94)
      {
        this.leftWall[7].add(doors[21]);
      }
      else if(i == 95)
      {
        this.leftWall[7].add(walls[72]);
      }
      else if(i == 96)
      {
        this.topWall[8].add(walls[73]);
      }
      else if(i == 97)
      {
        this.topWall[8].add(doors[22]);
      }
      else if(i == 98)
      {
        this.topWall[8].add(walls[74]);
      }
      else if(i == 99)
      {
        this.rightWall[8].add(walls[75]);
      }
      else if(i == 100)
      {
        this.rightWall[8].add(walls[76]);
      }
      else if(i == 101)
      {
        this.rightWall[8].add(walls[77]);
      }
      else if(i == 102)
      {
        this.bottomWall[8].add(walls[78]);
      }
      else if(i == 103)
      {
        this.bottomWall[8].add(walls[79]);
      }
      else if(i == 104)
      {
        this.bottomWall[8].add(walls[80]);
      }
      else if(i == 105)
      {
        this.leftWall[8].add(walls[81]);
      }
      else if(i == 106)
      {
        this.leftWall[8].add(doors[23]);
      }
      else if(i == 107)
      {
        this.leftWall[8].add(walls[82]);
      }
      else if(i >= 108 && i < 157)
      {
        this.tiles[0].add(floors[i - 108]);
      }
      else if(i >= 157 && i < 206)
      {
        this.tiles[1].add(floors[i - 108]);
      }
      else if(i >= 206 && i < 255)
      {
        this.tiles[2].add(floors[i - 108]);
      }
      else if(i >= 255 && i < 304)
      {
        this.tiles[3].add(floors[i - 108]);
      }
      else if(i >= 304 && i < 353)
      {
        this.tiles[4].add(floors[i - 108]);
      }
      else if(i >= 353 && i < 402)
      {
        this.tiles[5].add(floors[i - 108]);
      }
      else if(i >= 402 && i < 451)
      {
        this.tiles[6].add(floors[i - 108]);
      }
      else if(i >= 451 && i < 500)
      {
        this.tiles[7].add(floors[i - 108]);
      }
      else if(i >= 500 && i < 549)
      {
        this.tiles[8].add(floors[i - 108]);
      }
    }
    
    for(int i = 0; i < 9; i++)
    {
      bottomWall[i].add(generalLocation[i]);
      bottomWall[i].add(properties[i]);
      bottomWall[i].add(specificLocation[i]);
    }
    
    floors[212].setBackground(Color.black);
    floors[213].setBackground(Color.black);
    floors[214].setBackground(Color.black);
    floors[219].setBackground(Color.black);
    floors[220].setBackground(Color.black);
    floors[221].setBackground(Color.black);
    floors[226].setBackground(Color.black);
    floors[227].setBackground(Color.black);
    floors[228].setBackground(Color.black);
    
    buttonType[320] = 4;
    buttonType[321] = 4;
    buttonType[322] = 4;
    buttonType[327] = 4;
    buttonType[328] = 4;
    buttonType[329] = 4;
    buttonType[334] = 4;
    buttonType[335] = 4;
    buttonType[336] = 4;
    buttonType[312] = 5;
    buttonType[313] = 5;
    buttonType[314] = 5;
    buttonType[315] = 5;
    buttonType[316] = 5;
    buttonType[319] = 5;
    buttonType[326] = 5;
    buttonType[330] = 5;
    buttonType[333] = 5;
    buttonType[337] = 5;
    buttonType[340] = 5;
    buttonType[341] = 5;
    buttonType[342] = 5;
    buttonType[343] = 5;
    buttonType[344] = 5;
    buttonType[323] = 5;
    
    buttonType[24 + 108] = 6;
    buttonType[25 + 108] = 6;
    buttonType[26 + 108] = 6;
    buttonType[27 + 108] = 6;
    buttonType[31 + 108] = 6;
    buttonType[38 + 108] = 6;
    buttonType[45 + 108] = 6;
    buttonType[70 + 108] = 6;
    buttonType[71 + 108] = 6;
    buttonType[72 + 108] = 6;
    buttonType[73 + 108] = 6;
    buttonType[74 + 108] = 6;
    buttonType[75 + 108] = 6;
    buttonType[76 + 108] = 6;
    buttonType[80 + 108] = 6;
    buttonType[87 + 108] = 6;
    buttonType[94 + 108] = 6;
    buttonType[119 + 108] = 6;
    buttonType[120 + 108] = 6;
    buttonType[121 + 108] = 6;
    buttonType[122 + 108] = 6;
    buttonType[129 + 108] = 6;
    buttonType[136 + 108] = 6;
    buttonType[143 + 108] = 6;
    buttonType[150 + 108] = 6;
    buttonType[157 + 108] = 6;
    buttonType[164 + 108] = 6;
    buttonType[171 + 108] = 6;
    buttonType[172 + 108] = 6;
    buttonType[173 + 108] = 6;
    buttonType[174 + 108] = 6;
    buttonType[178 + 108] = 6;
    buttonType[185 + 108] = 6;
    buttonType[192 + 108] = 6;
    buttonType[199 + 108] = 6;
    buttonType[217 + 108] = 6;
    buttonType[223 + 108] = 6;
    buttonType[241 + 108] = 6;
    buttonType[248 + 108] = 6;
    buttonType[255 + 108] = 6;
    buttonType[262 + 108] = 6;
    buttonType[266 + 108] = 6;
    buttonType[267 + 108] = 6;
    buttonType[268 + 108] = 6;
    buttonType[269 + 108] = 6;
    buttonType[276 + 108] = 6;
    buttonType[283 + 108] = 6;
    buttonType[290 + 108] = 6;
    buttonType[297 + 108] = 6;
    buttonType[304 + 108] = 6;
    buttonType[311 + 108] = 6;
    buttonType[318 + 108] = 6;
    buttonType[319 + 108] = 6;
    buttonType[320 + 108] = 6;
    buttonType[321 + 108] = 6;
    buttonType[325 + 108] = 6;
    buttonType[332 + 108] = 6;
    buttonType[339 + 108] = 6;
    buttonType[346 + 108] = 6;
    buttonType[353 + 108] = 6;
    buttonType[360 + 108] = 6;
    buttonType[364 + 108] = 6;
    buttonType[365 + 108] = 6;
    buttonType[366 + 108] = 6;
    buttonType[367 + 108] = 6;
    buttonType[368 + 108] = 6;
    buttonType[369 + 108] = 6;
    buttonType[370 + 108] = 6;
    buttonType[374 + 108] = 6;
    buttonType[381 + 108] = 6;
    buttonType[388 + 108] = 6;
    buttonType[395 + 108] = 6;
    buttonType[402 + 108] = 6;
    buttonType[409 + 108] = 6;
    buttonType[413 + 108] = 6;
    buttonType[414 + 108] = 6;
    buttonType[415 + 108] = 6;
    buttonType[416 + 108] = 6;
    
    
   //picks which tiles will hide the keys, lasts until line 919.
    for(int i = 0; i <= 6; i++)
    {
      Random r = new Random();
      int ra = r.nextInt(441) + 108;
      specialFloors[i] = ra;
      buttonType[ra] = 3;
      
      if(buttonType[specialFloors[i]] == 4 || buttonType[specialFloors[i]] == 5)
      {
        int ra2 = r.nextInt(441) + 108;
        specialFloors[i] = ra2;
        buttonType[ra2] = 3;
        if(buttonType[specialFloors[i]] == 4 || buttonType[specialFloors[i]] == 5)
        {
          int ra3 = r.nextInt(441) + 108;
          specialFloors[i] = ra3;
          buttonType[ra3] = 3;
        }
        
      }
      
    }
    for(int i = 0; i < 7; i++)
    {
      int temp = specialFloors[i];
      temp = temp - 108;
      specialFloors[i] = temp;
    }
    
   //sets the starting location to visible. 
    startingLocation.setVisible(true);
  }
  
  //manages the clock, lasts until 967.
  public void start()
  {
    this.timer = new java.util.Timer();
    this.timerTask = new TimerTask() {
      public void run()
      {
        seconds++;
        if(seconds % 5 == 0)
        {
          System.out.println(Integer.toString(difficulty - seconds));
        }
        else if(seconds == difficulty + 1)
        {
          if(clockUsed == 16)
          {
            for(int i = 0; i < 441; i++)
            {
              floors[i].setBackground(Color.black);
            }
            System.out.println("all of the tiles have been consumed, leaving you to fall to your death");
            try
            {
              Thread.sleep(2500);
              System.exit(0);
            }
            catch(InterruptedException e)
            {
            }
          }
          System.out.println("some of the tiles were consumed");
          timer.cancel();
          timer.purge();
          seconds = 0;
          clockUsed++;
          clock();
        }
        
      }
    };
    
    timer.scheduleAtFixedRate(timerTask, 1000, 1000);
  }
  
  //chooses the random tiles to be consumed by the school, and controlls the game over screen.  lasts until line 1021
  public void clock()
  {
    Random r2 = new Random();
    int coveredTiles = 0;
    while(coveredTiles < 21)
    {
      int ra = r2.nextInt(441) + 108;
      if(buttonType[ra] == 4 || buttonType[ra] == 5 || buttonType[ra] == 6 || buttonType[ra] == 8)
      {
      }
      else if(buttonType[ra] == 3)
      {
        System.out.println("A KEY WAS CONSUMED");
        keysConsumed++;
        if(keysConsumed > 2)
        {
          canClick = false;
          System.out.println("you get the feeling in your gut that you have failed the objective\n" +
                             "youre devastated, and you lose all hope and your inner fight\n" + 
                             "as your will weakens, it allows the spirit of the school to posses your body\n" +
                             "you feel your body move on its own and walk towards the pit, and youre now powerless to stop it\n" +
                             "as you arrive at the pit, you take out a pencil and paper and your hands begin to write on their own\n" +
                             "you're disgusted and horrified as you realize your hands are writing a suicide note, meaning all these notes before you are also victims of the school\n" +
                             "at this point you realize that this is worth more than just yourself, its worth any future lives that enter this school\n" +
                             "you try to fight, as hard as you can, but you can no longer have control of your body\n" +
                             "you look at the completed note, and begin to cry, as you remove your shoes and gently tuck the note into your right shoe\n" +
                             "your body turns it's back to the pit, and as you take your last breath, your body tips on its own backwards\n" +
                             "as youre falling, the spirit stops possessing you, leaving yourself fully aware of the trap you just set for anyone in the future to enter this school\n" + 
                             "as youre fully aware of what youve done, you decide this is the best outcome for you anyway\n" +
                             "just before the school fades from view, you see all the schools tiles re-appear, setting the trap for it's next victim\n" +
                             "you close your eyes, fully defeated, and accept the darkness");
          try
          {
            Thread.sleep(10000000);
            System.exit(0);
          }
          catch(InterruptedException e)
          {
          }
        }
        floors[ra - 108].setBackground(Color.black);
        buttonType[ra] = 8;
        coveredTiles++;
      }
      else
      {
        floors[ra - 108].setBackground(Color.black);
        buttonType[ra] = 8;
        coveredTiles++;
      }
    }
    start();
  }
  
  //manages what buttons pressed do what events,  controlls doors, floors, keys. broken tiles. etc.  lasts until line 1541.
  public void actionPerformed(ActionEvent event)
  {
    if(canClick == true)
    {
      for(int i = 0; i < 83; i++)
      {
        if(walls[i] == event.getSource())
        {
          System.out.println("you searched the wall and found nothing, you get the feeling that none of the walls will have anything either.");
        }
      }
      for(int i = 0; i < 441; i++)
      {
        if(floors[i] == event.getSource())
        {
          int type = buttonType[i + 108];
          if( type == 4 || type == 8)
          {
            System.out.println("there is no tile on this floor, only nothingness.");
          }
          else if(type == 5)
          {
          }
          else if(type == 3)
          {
          }
          else if(type == 7)
          {
          }
          else
          {
            System.out.println("You searched a floor, and found nothing.");
          }
        }
      }
      for(int i = 0; i < 9; i++)
      {
        if(generalLocation[i] == event.getSource())
        {
          System.out.println("You're in an abandoned school.  the locations of the school are \n" +
                             "Starting Room, room 0\n" + 
                             "Abandoned teachers lounge, room 1\n" + 
                             "Classroom 1, room 2\n" + 
                             "Cafeteria, room 3\n" + 
                             "Courtyard W/Pit room 4\n" + 
                             "Classroom 2, room 5\n" + 
                             "Nurse's office, room 6\n" + 
                             "Front Entrance, room 7\n" + 
                             "Classroom 3, room 8\n"); 
          
        }
      }
      
      for(int i = 0; i < 9; i++)
      {
        if(properties[i] == event.getSource())
        {
          System.out.println("the properties of this room is that there are keys hidden in the floor tiles that you have to find by searching them.");
        }
      }
      
      if(doors[0] == event.getSource())
      {
        frame[0].setVisible(false);
        frame[1].setVisible(true);
        System.out.println("You found a door, and you walked through it");
      }
      else if(doors[1] == event.getSource())
      {
        System.out.println("this door is locked");
      }
      else if(doors[2] == event.getSource())
      {
        frame[1].setVisible(false);
        frame[2].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[3] == event.getSource())
      {
        frame[1].setVisible(false);
        frame[3].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[4] == event.getSource())
      {
        frame[1].setVisible(false);
        frame[0].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[5] == event.getSource())
      {
        frame[2].setVisible(false);
        frame[4].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[6] == event.getSource())
      {
        frame[2].setVisible(false);
        frame[1].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[7] == event.getSource())
      {
        frame[3].setVisible(false);
        frame[1].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[8] == event.getSource())
      {
        frame[3].setVisible(false);
        frame[4].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[9] == event.getSource())
      {
        frame[3].setVisible(false);
        frame[6].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[10] == event.getSource())
      {
        frame[4].setVisible(false);
        frame[2].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[11] == event.getSource())
      {
        frame[4].setVisible(false);
        frame[5].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[12] == event.getSource())
      {
        frame[4].setVisible(false);
        frame[6].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[13] == event.getSource())
      {
        frame[4].setVisible(false);
        frame[3].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[14] == event.getSource())
      {
        frame[5].setVisible(false);
        frame[2].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[15] == event.getSource())
      {
        frame[5].setVisible(false);
        frame[7].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[16] == event.getSource())
      {
        frame[5].setVisible(false);
        frame[4].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[17] == event.getSource())
      {
        frame[6].setVisible(false);
        frame[4].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[18] == event.getSource())
      {
        frame[6].setVisible(false);
        frame[7].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[19] == event.getSource())
      {
        frame[7].setVisible(false);
        frame[5].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[20] == event.getSource())
      {
        frame[7].setVisible(false);
        frame[8].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[21] == event.getSource())
      {
        frame[7].setVisible(false);
        frame[6].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(doors[22] == event.getSource())
      {
        frame[8].setVisible(false);
        trapRoom.setVisible(true);
        System.out.println("you've entered into the janitors closet.  and you close the door \n" + 
                           "behind you youve realized what youve done as the door molds into the wall. \n" + 
                           "your inner fight begins to fade until the spirit of the school possess you \n" + 
                           "your body passively waits until all the school tiles give out and you fall into the pit.\n" +
                           "as you are falling you see the tiles of the school come back to existance, as it sets itself up for its next victim.");
        timer.cancel();
        timer.purge();
        try
        {
          Thread.sleep(12000);
        }
        catch(InterruptedException e)
        {
        }
        System.exit(0);
        
        
        
        
      }
      else if(doors[23] == event.getSource())
      {
        frame[8].setVisible(false);
        frame[7].setVisible(true);
        System.out.println("you found a door, and you walked through it");
      }
      else if(startingRoomButtons[12] == event.getSource())
      {
        startingLocation.setVisible(false);
        frame[0].setVisible(true);
        System.out.println("Youve entered the school!  start clicking on the floor to search for keys.");
        start();
      }
      else if(doors[24] == event.getSource())
      {
        if(this.keys > 0)
        {
          System.out.println("Youve escaped the school!  Cherish your life from today on.");
          try
          {
            Thread.sleep(2500);
            System.exit(0);
          }
          catch(InterruptedException e)
          {
          }
        }
        else
        {
          System.out.println("The door is locked!  You only have " + this.keys  + " key(s).  Come back when you have 5 keys.");
        }
      }
      else if(specificLocation[0] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Abandoned teachers lounge, room 1");
      }
      else if(specificLocation[1] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Starting Room, room 0, Classroom 1, room 2,  Cafeteria, room 3");
      }
      else if(specificLocation[2] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Abandoned teachers lounge, room 1, Courtyard W/Pit room 4");
      }
      else if(specificLocation[3] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Abandoned teachers lounge, room 1, Courtyard W/Pit room 4");
      }
      else if(specificLocation[4] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Classroom 1, room 2, Cafeteria, room 3, Classroom 2, room 5, Nurse's office, room 6");
      }
      else if(specificLocation[5] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Courtyard W/Pit room 4, Front Entrance, room 7");
      }
      else if(specificLocation[6] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Courtyard W/Pit room 4, Front Entrance, room 7");
      }
      else if(specificLocation[7] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Classroom 2, room 5, Nurse's office, room 6, Classroom 3, room 8");
      }
      else if(specificLocation[8] == event.getSource())
      {
        System.out.println("the rooms you can go to from here are: Front Entrance, room 7");
      }
      else if(floors[specialFloors[0]] == event.getSource()) 
      {
        if(this.used0 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used0 = true;
          buttonType[specialFloors[0] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[0] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
        }
      }
      else if(floors[specialFloors[1]] == event.getSource()) 
      {
        if(this.used1 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used1 = true;
          buttonType[specialFloors[1] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[1] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
          
          
          
        }
      }
      else if(floors[specialFloors[2]] == event.getSource()) 
      {
        if(this.used2 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used2 = true;
          buttonType[specialFloors[2] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[2] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
          
        }
      }
      else if(floors[specialFloors[3]] == event.getSource()) 
      {
        if(this.used3 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used3 = true;
          buttonType[specialFloors[3] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[3] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
          
        }
      }
      else if(floors[specialFloors[4]] == event.getSource()) 
      {
        if(this.used4 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used4 = true;
          buttonType[specialFloors[4] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[4] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
          
        }
      }
      else if(floors[specialFloors[5]] == event.getSource()) 
      {
        if(this.used5 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used5 = true;
          buttonType[specialFloors[5] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[5] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
          
        }
      }
      else if(floors[specialFloors[6]] == event.getSource()) 
      {
        if(this.used6 == false)
        {
          this.keys++;
          System.out.println("you found a key");
          this.used6 = true;
          buttonType[specialFloors[6] + 108] = 7; 
        }
        else
        {
          if(buttonType[specialFloors[6] + 108] != 8)
          {
            System.out.println("You've already collected this key, you need to find different ones.");
          }
          
        }
      }
      else if(floors[204] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" + 
      "Dear family and friends.  I regret to inform you that i no longer have the strength to continue pretending.\n" +
      "my inner hate has consumed me whole, and i dont want to hold you back any longer.\n" + 
      "forget me, stephen");
      }
      else if(floors[205] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "beloved maggie.  I hope youll forgive me.  you know i would do anything for you but i cant see the future anymore\n" +
      "all i can see now is the black inside me and i want to stay to make your life better but i have to do this for myslef\n" +
      "forgive me, allison.");
      }
      else if(floors[206] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "jefferson high.  I hope you never forget what you did to me.  you made me do this. you were never there for me.\n" +
      "i hope youre forced to close after im gone because you dont deserve to remain a functional school.\n" + 
      "go to hell, joe.");
      }
      else if(floors[207] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "dear mom and dad.  Im sorry i wasted all your time and money.  you always cared about me and were there for me and i still can't make it work.\n" + 
      "i hope you'll forgive me but i know you probably wont.  even though you were great parents i still couldnt make it on my own.\n" +
      "Im sorry, jenny.");
      }
      else if(floors[208] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" + 
      "dear anyone.  I feel so alone.  i havent been a part of a family all my life, no friends either.\n" + 
      "and now i see death here, and i know that if i was gone, nothing of significance will change, and its very tempting.\n" +
      "i hope at least whoever finds this note will remember me");
      }
      else if(floors[211] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "What is anything really.  ive lived a full life and i still dont know whether anything is truly significant or not.\n" + 
      "I can stop living right here and right now and ill never even get to know if a choice i made will ever matter.\n" +
      "lets find out.");
      }
      else if(floors[215] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "everything ive ever tried has blown up in my face.  lovers, jobs, friends...\n" + 
      "i know that nothing i will do from here on out will be any better either, so good riddance.\n" + 
      "tom");
      }
      else if(floors[218] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "you ever wonder what happens when you die?  what if God actually is real? i know id go to hell.\n" + 
      "but honestly, hell sounds better than here because at least id know im in somewhere thats real.\n" + 
      "save me, Amy");
      }
      else if(floors[222] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "Ive always been afraid of dying.  its been a crippling fear that has always been unreasonable.\n" + 
      "But now here i am, staring death in the face, and its exciting.  theyve always said face your fears, and i guess its my turn.\n" + 
      "johnnie");
      }
      else if(floors[225] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "i hate this.  i hate this school, i hate this world, i hate my friends, i hate my family, and i hate you.\n" + 
      "worst of it all, i hate me.  i know all my hate for everything else is my fault and that just makes me more angry.\n" +
      "i cant hate if i cant feel.");
      }
      else if(floors[229] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "Everything in my life is good.  my friends, family, job, wife, kids, they all love me.\n" +
      "so why cant i stop wanting to die?  I have the life that so many people would dream of.\n" +
      "maybe if i give up my life somebody else can have it.");
      }
      else if(floors[232] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "Dont listen to any of the other notes.  theyre a trap, theyre writing them against their will!  Nobody here wants to die!\n" +
      "The school, its possessing us!  I can feel it try to take away my soul!  ill never let it! i know i can make it out of here alive!\n" + 
      "but death does seem pretty tempting, and if you think about it, its the fastest way out of this school too!  maybe everyone else had the right idea.");
      }
      else if(floors[233] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "dear anyone who reads this.  im scared, im lost, and im alone, and if youre reading this, than you probably are too.\n" + 
      "Ive carried this charm with me all of my life and its helped me greatly when im scared or alone, and i want you to have it.\n" + 
      "If you take this panda sculpture and hold it you know that you are connected with nature, and that no one can hurt you. please use it.\n" +
      "You recieved the panda charm.  Its too heavy to carry around.  you throw it in the pit.");
      }
      else if(floors[234] == event.getSource())
      {
        System.out.println("There are no shoes or notes here, just an eerie empty space, as if its waiting for something.");
      }
      else if(floors[235] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a cleanly folded note sitting in the right shoe, you decide to read it.\n" +
      "If youve been reading all these notes, youve been wasting all your time!  The school around you doesnt stop crumbling for anything or anyone!\n" + 
      "Stop Reading!  You only have a limited amount of time to get out of here, otherwise you're gunna end up like all of us!");
      }
      else if(floors[236] == event.getSource())
      {
        System.out.println("You found a pair of shoes here, facing the pit, with a note sitting in the right shoe, you decide to read it.\n" +
      "The note is empty, its just a crumpled piece of paper, full of blood.  It looks like whoever tried to write this note was doing it by coughing blood randomly on the paper");
      }
      
    }
  }
}