import java.awt.*;    
public class ButtonExample {    
public static void main (String[] args) {   
	Frame frm = new Frame("Java AWT frame");
	Label lbl = new Label("Welcome to Roseindia.net Tutorial.",Label.CENTER);
	frm.add(lbl);
	frm.setSize(400,400);
	frm.setVisible(true);
  
    Button b = new Button("Click Here");   
  
    // set the position frmor the button in frmrame   
    b.setBounds(30,100,80,30);    
lbl.setBounds(20,80,80,30);
  
    // add button to the frmrame  
    frm.add(b);    
    // set size, layout and visibility ofrm frm
frm.setLayout(null);
    b.setVisible(true);  
  
}    
}