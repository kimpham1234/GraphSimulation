import java.awt.EventQueue;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class SampleScene {

	public static void main(String[] args) {
		SceneFrame frame = new SceneFrame();
		EventQueue.invokeLater(()->{
			 frame.setVisible(true);
		 });
	}

}
