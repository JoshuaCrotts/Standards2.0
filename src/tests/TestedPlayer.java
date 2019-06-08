package tests;

import java.awt.Color;

import com.joshuacrotts.particles.DragParticle;
import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardFade;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardParticle;
import com.joshuacrotts.standards.StandardParticleHandler;
import com.joshuacrotts.standards.StdOps;

public class TestedPlayer extends StandardGame
{

    private Player player;
    private StandardHandler particleHandler;

    private StandardFade stdFade = new StandardFade( Color.RED, Color.YELLOW, 0.05f );

    public TestedPlayer( int width, String title )
    {
        super( width, title );

        this.consoleFPS = false;

        this.particleHandler = new StandardParticleHandler();

        this.player = new Player( 200, 120, this );
    }

    @Override
    public void tick()
    {
        //Pass the handler, and it will tick.
        StandardHandler.Handler( player.getTrail() );
        StandardHandler.Object( player );

        for ( int i = 0; i < 100; i ++ )
        {
            new DragParticle( player.getX(), player.getY(), 3, particleHandler );
        }

        StandardHandler.Handler( particleHandler );
    }

    @Override
    public void render()
    {

        StandardDraw.Handler( player.getTrail() );
        StandardDraw.Object( player );
        StandardDraw.Handler( particleHandler );

        StandardDraw.text( "Hello World!", 100, 100, "", 50, Color.YELLOW );
    }

    public static void main( String[] args )
    {
        new TestedPlayer( 1920, "Test" );
    }

}
