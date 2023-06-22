package view.ui.menu;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MenuAnimation {

    public static void showMenu(final Component component, final MenuItem item, final MigLayout layout, final boolean show) {
        final int height = component.getPreferredSize().height;
        final Animator animator = new Animator(300, new TimingTargetAdapter() {
            @Override
            public void timingEvent(final float fraction) {
                final float interpolated = show ? fraction : 1f - fraction;
                layout.setComponentConstraints(component, "h " + height * interpolated + "!");
                item.setAnimate(interpolated);
                component.revalidate();
                item.repaint();
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.start();
    }
}
