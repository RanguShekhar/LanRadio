import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PanelWithButton extends JPanel
{
    // Field members
    private AtomicBoolean paused;
    private JTextArea textArea;
    private JButton button;
    private JButton buttonn;
	
    private Thread threadObject;
	
	int i=0;

    /**
     * Constructor
     */
    public PanelWithButton()
    {
        paused = new AtomicBoolean(false);
        //textArea = new JTextArea(5, 30);
        button = new JButton();
        buttonn = new JButton("Stop");
	
	

        initComponents();
    }

    /**
     * Initializes components
     */
    public void initComponents()
    {
        // Construct components
        //textArea.setLineWrap(true);
       // textArea.setWrapStyleWord(true);
       // add( new JScrollPane(textArea));
			//this.setLayout(null);
        button.setBounds(100,400,50,50);
	button.setVisible(true);
        buttonn.setBounds(170,100,50,50);
        button.setText("Pause");
	
        button.addActionListener(new ButtonListener());
        //buttonn.addActionListener(new ButtonListener());
        add(button);
        add(buttonn);
	
		buttonn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(i+1);
				
			}
		});

        // Runnable that continually writes to text area
        Runnable runnable = new Runnable()
        {
            @Override
            public void run() 
            {
                while(true)
                {
                   	JavaSoundRecorder aa=new JavaSoundRecorder();
			try
			{
				aa.play();
			}	
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
                        if(paused.get())
                        {
				 
                            synchronized(threadObject)
                            {
                                // Pause
                                try 
                                {
                                    threadObject.wait();
                                } 
                                catch (InterruptedException e) 
                                {
                                }
				 
                            }
                        

                        // Write to text area
                       


                        // Sleep
                        try 
                        {
                            Thread.sleep(500);
                        } 
                        catch (InterruptedException e) 
                        {
                        }
                    }
                }
            }
        };
        threadObject = new Thread(runnable);
        threadObject.start();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }

    /**
     * Button action listener
     * @author meherts
     *
     */
    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            if(!paused.get())
            {
                button.setText("Start");
                paused.set(true);
            }
            else
            {
                button.setText("Pause");
                paused.set(false);

                // Resume
                synchronized(threadObject)
                {
                    threadObject.notify();
                }
            }
			
        }
		
    }
}
