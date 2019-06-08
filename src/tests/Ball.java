package tests;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardID;

public class Ball extends StandardGameObject
{

    private StandardGame stdGame;

    public Ball( int x, int y, StandardGame g )
    {
        super( x, y, StandardID.Entity );

        this.stdGame = g;

        this.width = 15;
        this.height = 15;

        this.velX = 4;
        this.velY = 4;
    }

    public void tick()
    {

        if ( this.x <= 0 || this.x >= stdGame.width() - this.width )
        {
            this.velX =  - this.velX;
        }

        if ( this.y <= 0 || this.y >= stdGame.height() - this.height )
        {
            this.velY =  - this.velY;
        }

        this.x += velX;
        this.y += velY;
    }

    public void render( Graphics2D g2 )
    {
        g2.setColor( Color.red );
        g2.fillOval( ( int ) x, ( int ) y, width, height );
    }
}
