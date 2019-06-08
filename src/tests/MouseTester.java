package tests;

import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardHandler;

public class MouseTester extends StandardGame
{

    private static final long serialVersionUID = 1L;
    private MouseParticle mP;

    public MouseTester( int width, int height, String title )
    {
        super( width, height, title );

        this.mP = new MouseParticle( mouse );
    }

    @Override
    public void tick()
    {
        if ( mP != null )
        {
            StandardHandler.Object( mP );
        } else
        {
            System.out.println( "Null." );
        }

    }

    @Override
    public void render()
    {

        StandardDraw.Object( this.mP );
    }

    public static void main( String[] args )
    {
        new MouseTester( 640, 640, "MouseTester" );
    }

}
