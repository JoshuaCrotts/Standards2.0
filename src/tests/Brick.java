package tests;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardID;

public class Brick extends StandardGameObject
{

    public Brick( int x, int y )
    {
        super( x, y, StandardID.Brick );

        this.width = 80;
        this.height = 20;
    }

    public void tick()
    {

    }

    public void render( Graphics2D g2 )
    {
        g2.setColor( Color.WHITE );
        g2.fillRect( ( int ) x, ( int ) y, width, height );
    }

}
