import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bullet {

    private Bitmap bulletBitmap;
    private int x, y; // Bullet position
    private int speed; // Bullet speed
    private Rect collisionRect; // Rectangular area for collision detection

    public Bullet(Context context, Bitmap bulletBitmap, int startX, int startY, int speed) {
        this.bulletBitmap = bulletBitmap;
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        this.collisionRect = new Rect(x, y, x + bulletBitmap.getWidth(), y + bulletBitmap.getHeight());
    }

    public void update() {
        // Update the bullet's position based on speed or any other logic
        x += speed;
        collisionRect.set(x, y, x + bulletBitmap.getWidth(), y + bulletBitmap.getHeight());
    }

    public void draw(Canvas canvas, Paint paint) {
        // Draw the bullet on the canvas
        canvas.drawBitmap(bulletBitmap, x, y, paint);
    }

    public Rect getCollisionRect() {
        return collisionRect;
    }
}
public class Enemy {

    private Bitmap enemyBitmap;
    private int x, y; // Enemy position
    private int speed; // Enemy speed
    private Rect collisionRect; // Rectangular area for collision detection

    public Enemy(Context context, Bitmap enemyBitmap, int startX, int startY, int speed) {
        this.enemyBitmap = enemyBitmap;
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        this.collisionRect = new Rect(x, y, x + enemyBitmap.getWidth(), y + enemyBitmap.getHeight());
    }

    public void update() {
        // Update the enemy's position based on speed or any other logic
        x -= speed;
        collisionRect.set(x, y, x + enemyBitmap.getWidth(), y + enemyBitmap.getHeight());
    }

    public void draw(Canvas canvas, Paint paint) {
        // Draw the enemy on the canvas
        canvas.drawBitmap(enemyBitmap, x, y, paint);
    }

    public Rect getCollisionRect() {
        return collisionRect;
    }

    public boolean checkCollision(Bullet bullet) {
        // Check if the enemy collides with a bullet
        return Rect.intersects(collisionRect, bullet.getCollisionRect());
    }
}