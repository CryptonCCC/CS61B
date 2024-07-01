public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[N];

        for (int i = 0; i < N; i++){
            double x = in.readDouble();
            double y = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(x, y, vx, vy, mass, img);
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universe_radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        StdDraw.setScale(-universe_radius, universe_radius);
        StdDraw.enableDoubleBuffering();

        int N = planets.length;
        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
        
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
