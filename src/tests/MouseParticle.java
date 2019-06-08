package tests;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.andrewmatzureff.commands.Command;
import com.andrewmatzureff.input.Mouse;
import com.joshuacrotts.particles.DragParticle;
import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardParticleHandler;

public class MouseParticle extends StandardGameObject/*
 * implements MouseListener
 */
{

    private static final int NUM_OF_PARTICLES = 500;

    private StandardHandler particleHandler;

    private class TestParticle extends Command
    {

        private StandardHandler sh;
        private Mouse mouse;

        public TestParticle( StandardHandler sh, Mouse mouse )
        {
            this.sh = sh;
            this.mouse = mouse;
        }

        public void execute()
        {
            int x = mouse.x;
            int y = mouse.y;

            //System.out.println(x+","+y);
            //if(sh.size() < 100000)
            for ( int i = 0; i < NUM_OF_PARTICLES; i ++ )
            {
                new DragParticle( x, y, 4d, sh );
            }

        }
    }

    public MouseParticle( Mouse mouse )
    {
        this.particleHandler = new StandardParticleHandler();
        new TestParticle( particleHandler, mouse ).bind( mouse, MouseEvent.BUTTON1 );
    }

    @Override
    public void tick()
    {

        StandardHandler.Handler( this.particleHandler );
        //particleHandler.clean();

        //System.out.println(this.particleHandler.size());
    }

    @Override
    public void render( Graphics2D g2 )
    {

        StandardDraw.Handler( particleHandler );
        //StandardDraw.color(Color.white);
        StandardDraw.Renderer.drawString( "Particles: " + particleHandler.size(), 25, 25 );
    }

    /*
     * @Override public void mousePressed(MouseEvent e) {
     *
     * int x = e.getX(); int y = e.getY();
     *
     *
     * for(int i = 0; i < NUM_OF_PARTICLES; i++)
     * this.particleHandler.addEntity(new StandardParticle(x, y, 1d,
     * this.particleHandler));
     *
     * }
     *
     * @Override public void mouseClicked(MouseEvent e) { // TODO Auto-generated
     * method stub
     *
     * }
     *
     *
     * @Override public void mouseReleased(MouseEvent e) { // TODO
     * Auto-generated method stub
     *
     * }
     *
     * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
     * method stub
     *
     * }
     *
     * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
     * method stub
     *
     * }
     *
     *
     */
}
