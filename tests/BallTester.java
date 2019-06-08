package tests;

import com.joshuacrotts.particles.DragParticle;
import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardID;

public class BallTester extends StandardGame
{

    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;

    private StandardHandler handler;

    public BallTester( int width, int height, String title )
    {
        super( width, height, title );

        this.paddle = new Paddle( 200, 590, this );
        this.bricks = new Brick[ 8 ];
        this.ball = new Ball( 300, 300, this );
        this.handler = new StandardHandler();

        for ( int i = 0, x = 0, y = 80; i < bricks.length; i ++, x += 80 )
        {
            bricks[i] = new Brick( x, 80 );
            this.handler.addEntity( bricks[i] );
        }

        this.handler.addEntity( paddle );
        this.handler.addEntity( ball );

        StartGame();

    }

    @Override
    public void tick()
    {

        StandardHandler.Handler( ball.getTrail() );

        for ( int i = 0; i < this.handler.size(); i ++ )
        {
            if ( this.handler.get( i ).getId() == StandardID.Entity )
            {

                StandardGameObject b = this.handler.get( i );

                for ( int j = 0; j < this.handler.size(); j ++ )
                {

                    StandardGameObject o = this.handler.get( j );

                    //Ball to brick collision
                    if ( o.getId() == StandardID.Brick )
                    {
                        if ( o.getBounds().intersects( b.getBounds() ) )
                        {

                            b.velY =  - b.velY;

                            for ( int k = 0; k < 100; k ++ )
                            {
                                new DragParticle( o.getX() + 40, o.getY() + 10, 4f, this.handler );
                            }

                            this.handler.removeEntity( o );
                        }
                    }

                    //Ball to paddle
                    if ( o.getId() == StandardID.Player )
                    {
                        if ( o.getBounds().intersects( b.getBounds() ) )
                        {
                            b.velY =  - b.velY;

                        }
                    }
                }
            }

            this.handler.get( i ).tick();
        }

    }

    @Override
    public void render()
    {

        StandardDraw.Handler( ball.getTrail() );
        StandardDraw.Object( ball );
        StandardDraw.Handler( handler );

    }

    public static void main( String[] args )
    {
        new BallTester( 640, 640, "Ball Test" );
    }

}
