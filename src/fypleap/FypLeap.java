
package fypleap;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.*;
import com.leapmotion.leap.HandList.*;
import com.leapmotion.leap.Hand.*;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

class LeapListener extends Listener{
    public Robot robot;
    public void onInit(Controller controller){
        System.out.println("Initialized ");
    } 
    
    public void onConnect(Controller controller){
        System.out.println("Sensor Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
    }
    public void onFocusGained (Controller controller){
        System.out.println("Focus gained");
    }

    public void  onFocusLost (Controller controller){
        System.out.println("Focus lost");        
    }
    
    public void onDisconnect(Controller controller){
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller){
        System.out.println("Exited");
    }


    
    public void onFrame(Controller c){
        try{robot = new Robot();} catch (Exception e) {}
        Frame frame = c.frame();
        InteractionBox box = frame.interactionBox();
        for(Finger f : frame.fingers()){
            if(f.type()==Finger.Type.TYPE_INDEX){
                Vector fingerPos= f.tipPosition();
                Vector boxFingerPos = box.normalizePoint(fingerPos);
                Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                robot.mouseMove((int)(screen.width*boxFingerPos.getX()),(int)(screen.height-boxFingerPos.getY()*screen.height));
                
            
            }
            else if(f.type()== Finger.Type.TYPE_MIDDLE){
                for (Gesture g: frame.gestures()){
            
        
            if(g.type()==Type.TYPE_KEY_TAP){
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                
                
        }
            
    }
            
    }
                
            
            
            }
        
        for (Gesture g: frame.gestures()){
            if(g.type()==Type.TYPE_CIRCLE){
                CircleGesture circle = new CircleGesture(g);
                if(circle.pointable().direction().angleTo(circle.normal())>=Math.PI/4){
                    robot.mouseWheel(1);
                    try{ Thread.sleep(50);}catch (Exception e){}
                   
                }
                else {
                    robot.mouseWheel(-1);
                    try{ Thread.sleep(50);}catch (Exception e){}
                   
                }
            }
            
            else if(g.type()==Type.TYPE_SCREEN_TAP){
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
            }
            
                
                
               
        
        }   
    }
    

//public void onFrame(Controller controller){
//        
//        
//        Frame frame = controller.frame();
//        System.out.println("Frame id: " + frame.id()
//                            + ",  Time Stamp: " + frame.timestamp()
//                            + ",    Hands:" + frame.hands().count()
//                            + ",    Gestures:"+ frame.gestures().count()
//                            + ",    Tools:"+ frame.tools().count()); 
//         
//        for (Hand hand :frame.hands()){
//            String handType;
//            handType = hand.isLeft() ? "Left hand" : "Right hand";
//            System.out.println("Hand Is:"+ handType);
//        }
//          for(Finger finger : frame.fingers()){
//              System.out.println("Finger Type: " + finger.type()
//                + " Finger Width: " + finger.width());
//              if(finger.width()>15){
//                  System.out.println("Hand is near");
//              }else{
//                  System.out.println("Hand is far");
//              }
//          }
//            GestureList gestures = frame.gestures();
//                    for(int i=0; i<gestures.count();i++){
//        Gesture gesture = gestures.get(i);
//        switch(gesture.type()){
//            case TYPE_CIRCLE:
//                CircleGesture circle = new CircleGesture(gesture);
//                String cw;
//                if(circle.pointable().direction().angleTo(circle.normal())<=Math.PI/4){
//                    cw="clockwise";
//                }
//                else{
//                    cw="anticlockwise";
//                }
//                System.out.println("Circle is" + cw);
//        }
//                        
//    }
//
//
//    }
}
public class FypLeap {

    public static void main(String[] args) {
        LeapListener listener = new LeapListener();
        Controller controller = new Controller();
        
        controller.addListener(listener);
        System.out.println("Press to Exit");
        try{
            System.in.read();
        }catch(IOException e){
            e.printStackTrace();
        }
        controller.removeListener(listener);
        
    }
    
    
}

