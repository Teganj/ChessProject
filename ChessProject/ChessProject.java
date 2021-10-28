import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
	Tegan Jennings
	X18303941
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	Boolean whitePieceMoveFirst;


	public ChessProject(){
		Dimension boardSize = new Dimension(600, 600);

		//Use a Layered Pane for this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		//Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout( new GridLayout(8, 8) );
		chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel( new BorderLayout() );
			chessBoard.add( square );

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
			else
				square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
		}

		// Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
			pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
		panels.add(pieces);
		for(int i=48;i < 56; i++){
			pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
		panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
		panels.add(pieces);
	}


	//This method checks if there is a piece present on a particular square.
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}


	//This is a method to check if a piece is a Black piece.
	private Boolean checkWhiteOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			opponent = true;
			if(tmp1.contains("King")){
				JOptionPane.showMessageDialog(null,"Game Over, White Wins");
				System.exit(0);
			}
		}
		else{
			opponent = false;
		}
		return opponent;
	}


	//This is a method to check if a piece is a White piece.
	private Boolean checkBlackOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			opponent = true;
			if(tmp1.contains("King")) {
				JOptionPane.showMessageDialog(null, "Game over, Black Wins!");
				System.exit(0);
			}
		}
		else{
			opponent = false;
		}
		return opponent;
	}


	//This method is called when we press the Mouse. So we need to find out what piece we have selected. We may also not have selected a piece!
	public void mousePressed(MouseEvent e){
		chessPiece = null;
		Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null) return;
		chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}


    //This method is used when the Mouse is released...we need to make sure the move was valid before putting the piece back on the board.
	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null) return;
		chessPiece.setVisible(false);
		Boolean success = false;
		Boolean progression = false;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length() - 4));
		Boolean validMove = false;
		System.out.println(pieceName);

		int landingX = (e.getX()/75);
		int landingY = (e.getY()/75);
		int xMovement = Math.abs((landingX-startX));
		int yMovement = Math.abs((landingY-startY));


		/*//King Code
		if(pieceName.contains("King")) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			//One Square at a time so xMovement && yMovement can't be zero
			if (xMovement > 1 || yMovement > 1 || (xMovement == 0 && yMovement == 0)) {
				validMove = false;
			} else {
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				} else {
					//1 square distance between opponent Kings
					if (!(piecePresent(e.getX(), e.getY()))) {
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
						} else {
							validMove = false;
						}
					}
				}
			}
		}
		//Queen Code
		else if(pieceName.contains("Queen")){
			//The Queen can move in a horizontal, vertical or diagonal direction as long as there are no pieces in the way.
			Boolean inTheWay = false;
			int distance = Math.abs(startX-landingX);
			if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
				validMove = false;
			}
			//Queen can now move horzontially or vertically
			else if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
				if(Math.abs(startX-landingX)!=0){
					if(startX-landingX > 0){
						for(int i = 0; i < xMovement; i++){
							if(piecePresent(initialX-(i*75), e.getY())){
								inTheWay = true;
								break;
							}
							else{
								inTheWay = false;
							}
						}
					}
					else{
						for(int i=0; i < xMovement; i++){
							if(piecePresent(initialX+(i*75),e.getY())){
								inTheWay = true;
								break;
							}
								else{
								inTheWay = false;
							}
						}
					}
				}
				else{
					if(startY-landingY > 0){
						for(int i=0; i < yMovement; i++){
							if(piecePresent(e.getX(),initialY-(i*75))){
								inTheWay = true;
								break;
							}
							else{
								inTheWay = false;
							}
						}
					}
					else{
						for(int i=0; i < yMovement; i++){
							if(piecePresent(e.getX(),initialY+(i*75))){
								inTheWay = true;
								break;
							}
							else{
								inTheWay = false;
							}
						}
					}
				}
					if(inTheWay){
					validMove = false;
				}
				else{
					//Checks if the piece in the way is an opponent piece and returns a Boolean
					validMove = true;
				}
			}
			//The code below allows the queen to move like a Bishop
			else{
				if(Math.abs(startX-landingX) == Math.abs(startY-landingY)){
					if((startX-landingX < 0)&&(startY-landingY < 0)){
						for(int i = 0; i < distance; i++){
							if(piecePresent((initialX+(i*75)), initialY+(i*75))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX < 0)&&(startY-landingY > 0)){
						for(int i = 0; i < distance; i++){
							if(piecePresent((initialX+(i*75)),initialY-(i*75))){
								inTheWay=true;
							}
						}
					}
					else if((startX-landingX > 0)&&(startY-landingY > 0)){
						for(int i = 0; i < distance; i++){
							if(piecePresent((initialX-(i*75)),initialY-(i*75))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX > 0)&&(startY-landingY < 0 )){
						for(int i = 0; i < distance; i++){
							if(piecePresent((initialX-(i*75)), initialY+(i*75))){
								inTheWay = true;
							}
						}
					}
					if(inTheWay){
						validMove = false;
					}
					else{
						//Checks if the piece in the way is an opponent piece and returns a Boolean
						validMove = true;
					}
				}
				else{
					validMove = false;
				}
			}
		}
		*/
		//Rook Code

		if(pieceName.contains("Rook")){
			Boolean inTheWay = false;
			if(((landingX<0)||(landingX>7))||((landingY<0)||(landingY>7))){
				validMove = false;
			}
			else{
				if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)==0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
					if(Math.abs(startX-landingX)!=0){
						if(startX-landingX>0){
							for( int i=0; i < xMovement; i++){
								if(piecePresent(initialX-(i*75), e.getY())){
									inTheWay = true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
						else{
							for(int i=0; i< xMovement; i++){
								if(piecePresent(initialX+(i*75), e.getY())){
									inTheWay = true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
					}
					else{
						if(startY-landingY>0){
							for(int i=0; i< yMovement; i++){
								if(piecePresent(e.getX(), initialY-(i*75))){
									inTheWay = true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
						else{
							for(int i=0; i < yMovement; i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									inTheWay = true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
					}
					if(inTheWay){
						validMove = false;
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOpponent(e.getX(), e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
							else{
								if(checkBlackOpponent(e.getX(), e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = true;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}
		//Bishop Code
		else if(pieceName.contains("Bishop")){
			Boolean inTheWay = false;
			int distance = Math.abs(startX-landingX);
			if(((landingX<0)||(landingX>7)||((landingY<0)||landingY>7))){
				validMove = false;
			}
			else{
				validMove = true;
				if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
					if((startX-landingX <0)&&(startY-landingY<0)){
						for(int i=0; i<distance; i++){
							if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX < 0)&&(startY-landingY > 0)){
						for( int i=0; i < distance; i++){
							if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX > 0)&&(startY-landingY>0)){
						for(int i=0; i< distance; i++){
							if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX > 0)&&(startY-landingY<0)){
						for(int i=0; i<distance; i++){
							if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
								inTheWay = true;
							}
						}
					}
					if(inTheWay){
						validMove = false;
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOpponent(e.getX(), e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
							else{
								if(checkBlackOpponent(e.getX(), e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						 else{
							validMove = true;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}
		//Knight Code
		else if (pieceName.contains("Knight")) {
			if(((landingX<0)||(landingX>7))||((landingY<0)||landingY>7)){
				validMove = false;
			}
			else{
				if(((landingX==startX+1)&&(landingY==startY+2))||((landingX==startX-1)&&(landingY == startY+2))||((landingX==startX+2)&&(landingY == startY+1))||((landingX==startX-2)&&(landingY==startY+1))||((landingX == startX+1)&&(landingY == startY-2))||((landingX == startX-1)&&(landingY == startY-2))||((landingX == startX+2)&&(landingY == startY-1))||((landingX == startX-2)&&(landingY == startY-1))){
					if(piecePresent(e.getX(), (e.getY()))){
						if(pieceName.contains("White")){
							if(checkWhiteOpponent(e.getX(), e.getY())){
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							if(checkBlackOpponent(e.getX(), e.getY())){
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
					}
					else{
						validMove = true;
					}
				}
				else{
					validMove = false;
				}
			}
		}
		//Black Pawn Code
		else if (pieceName.contains("BlackPawn")) {
			if (startY == 6) {
				if ((startX == (e.getX() / 75)) && ((((e.getY() / 75) - startY) == -1) || ((e.getY() / 75) - startY) == -2)) {
					if ((((e.getY() / 75) - startY) == -2)) {
						if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
							validMove = true;
						} else {
							validMove = false;
						}
					} else {
						if ((!piecePresent(e.getX(), (e.getY())))) {
							validMove = true;
						} else {
							validMove = false;
						}
					}
				} else {
					validMove = false;
				}
			} else {
				int newY = e.getY() / 75;
				int newX = e.getX() / 75;
				if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
					if ((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX + 1) && (startX + 1 <= 7))) || ((newX == (startX - 1)) && (startX - 1 >= 0))))) {
						if (checkWhiteOpponent(e.getX(), e.getY())) {
							validMove = true;
							if (startY == 6) {
								success = true;
							}
						} else {
							validMove = false;
						}
					} else {
						if (!piecePresent(e.getX(), (e.getY()))) {
							if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) {
								if (startY == 6) {
									success = true;
								}
								validMove = true;
							} else {
								validMove = false;
							}
						} else {
							validMove = false;
						}
					}
				} else {
					validMove = false;
				}
			}
		}
		//White Pawn Code
		 else if (pieceName.equals("WhitePawn")) {
			if (startY == 1) {
                int newX = e.getX()/75;
				if ((startX == (e.getX() / 75)) && ((((e.getY() / 75) - startY) == 1) || ((e.getY() / 75) - startY) == 2)) {
					if ((((e.getY() / 75) - startY) == 2)) {
						if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
							validMove = true;
						} else {
							validMove = false;
						}
					} else {
						if ((!piecePresent(e.getX(), (e.getY())))) {
							validMove = true;
						} else {
							validMove = false;
						}
					}
				} else {
					validMove = false;
				}
			} else {
				int newY = e.getY() / 75;
				int newX = e.getX() / 75;
				if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
					if ((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX + 1) && (startX + 1 <= 7))) || ((newX == (startX - 1)) && (startX - 1 >= 0))))) {
						if (checkWhiteOpponent(e.getX(), e.getY())) {
							validMove = true;
							if (startY == 6) {
								success = true;
							}
						} else {
							validMove = false;
						}
					} else {
						if (!piecePresent(e.getX(), (e.getY()))) {
							if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) {
								if (startY == 6) {
									success = true;
								}
								validMove = true;
							} else {
								validMove = false;
							}
						} else {
							validMove = false;
						}
					}
				} else {
					validMove = false;
				}
			}
		}

		 if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
			panels.add(pieces);
		}
		else{
			if(progression){
				int location = 0 + (e.getX()/75);
				if(c instanceof JLabel){
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel( new ImageIcon("BlackQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
					parent.add(pieces);
				}
			}
			else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
					parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
					parent.add(pieces);
				}
			}
			else{
				if (c instanceof JLabel){
					Container parent = c.getParent();
					parent.remove(0);
					parent.add( chessPiece );
				}
				else {
					Container parent = (Container)c;
					parent.add( chessPiece );
				}
				chessPiece.setVisible(true);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e) {
	}

	//Main method that gets the ball moving.
	public static void main(String[] args) {
		JFrame frame = new ChessProject();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}
}

