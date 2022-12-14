package falling.sand;

import falling.sand.input.Mouse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
public class Display extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private final JFrame frame;
    private static final String title = "3D Renderer";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static boolean running = false;

    private final SandManager sandManager;
    private final Mouse mouse;


    public Display( )
    {
        this.frame = new JFrame( );

        this.sandManager = new SandManager();
        this.mouse = new Mouse();

        Dimension size = new Dimension( WIDTH, HEIGHT );
        this.setPreferredSize( size );

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);


    }

    public static void main( String[ ] args )
    {
        Display display = new Display( );
        display.frame.setTitle( title );
        display.frame.add( display );
        display.frame.pack( );
        display.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        display.frame.setLocationRelativeTo( null );
        display.frame.setResizable( false );
        display.frame.setVisible( true );

        display.start( );
    }

    public synchronized void start( )
    {
        running = true;
        this.thread = new Thread( this, "falling.sand.Display" );
        this.thread.start( );
    }

    public synchronized void stop( )
    {
        running = false;
        try
        {
            this.thread.join( );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace( );
        }
    }

    @Override
    public void run( )
    {
        long lastTime = System.nanoTime( );
        long timer = System.currentTimeMillis( );
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;

        this.sandManager.init(this.mouse);

        while ( running )
        {
            long now = System.nanoTime( );
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while ( delta >= 1 )
            {
                update( );
                delta--;
                render( );
                frames++;
            }

            if ( System.currentTimeMillis( ) - timer > 1000 )
            {
                timer += 1000;
                this.frame.setTitle( title + " | " + frames + " fps" );
                frames = 0;
            }
        }

        stop( );
    }

    private void render( )
    {
        BufferStrategy bs = this.getBufferStrategy( );
        if ( bs == null )
        {
            this.createBufferStrategy( 3 );
            return;
        }

        Graphics g = bs.getDrawGraphics( );

        g.setColor( Color.BLACK );
        g.fillRect( 0, 0, WIDTH * 2, HEIGHT * 2 );

        this.sandManager.render(g);

        g.dispose( );
        bs.show( );

    }

    private void update( ) {
        // sim speed
        int simSpeed = 3;
        for (int i = 0; i < simSpeed; i++) {
            this.sandManager.update();
        }

    }

}