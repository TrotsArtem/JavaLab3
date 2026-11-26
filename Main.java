import java.util.Objects;
import java.util.Scanner;


class Ship {
    protected String name;
    protected double displacement; // водотоннажність, т
    protected int year;

    public Ship(String name, double displacement, int year) {
        this.name = name;
        this.displacement = displacement;
        this.year = year;
    }

    public void Show() {
        System.out.println("Корабель: " + name + ", водотоннажність: " + displacement + " т, рік побудови: " + year);
    }
}

class Steamship extends Ship {
    private double enginePower; // кВт
    private String fuelType;

    public Steamship(String name, double displacement, int year, double enginePower, String fuelType) {
        super(name, displacement, year);
        this.enginePower = enginePower;
        this.fuelType = fuelType;
    }

    @Override
    public void Show() {
        System.out.print("Пароплав ");
        super.Show();
        System.out.println("потужність: " + enginePower + " кВт, паливо: " + fuelType);
    }
}

class Sailboat extends Ship {
    private int mastCount;
    private double sailArea; // м2

    public Sailboat(String name, double displacement, int year, int mastCount, double sailArea) {
        super(name, displacement, year);
        this.mastCount = mastCount;
        this.sailArea = sailArea;
    }

    @Override
    public void Show() {
        System.out.print("Вітрильник ");
        super.Show();
        System.out.println("кількість щогл: " + mastCount + ", площа вітрил: " + sailArea + " м2");
    }
}

class Corvette extends Ship {
    private int gunCount;
    private int crew;

    public Corvette(String name, double displacement, int year, int gunCount, int crew) {
        super(name, displacement, year);
        this.gunCount = gunCount;
        this.crew = crew;
    }

    @Override
    public void Show() {
        System.out.print("Корвет ");
        super.Show();
        System.out.println("знарядь: " + gunCount + ", екіпаж: " + crew + " осіб");
    }
}

class Task1 {
    static void run() {
        int n = Main.readInt("Скільки кораблів ввести? ");
        Ship[] ships = new Ship[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nКорабель №" + (i + 1) + "");
            int type = Main.readInt("Тип (1-Корабель, 2-Пароплав, 3-Вітрильник, 4-Корвет): ");
            String name = Main.readString("Назва: ");
            double disp = Main.readDouble("Водотоннажність (т): ");
            int year = Main.readInt("Рік побудови: ");

            switch (type) {
                case 2:
                    double power = Main.readDouble("Потужність машини (кВт): ");
                    String fuel = Main.readString("Вид палива: ");
                    ships[i] = new Steamship(name, disp, year, power, fuel);
                    break;
                case 3:
                    int masts = Main.readInt("Кількість щогл: ");
                    double sails = Main.readDouble("Площа вітрил (м2): ");
                    ships[i] = new Sailboat(name, disp, year, masts, sails);
                    break;
                case 4:
                    int guns = Main.readInt("Кількість знарядь: ");
                    int crew = Main.readInt("Екіпаж (осіб): ");
                    ships[i] = new Corvette(name, disp, year, guns, crew);
                    break;
                default:
                    ships[i] = new Ship(name, disp, year);
            }
        }

        System.out.println("\nВведений масив");
        for (Ship s : ships) {
            s.Show();
        }
    }
}


class Task2 {
    static abstract class Figure3D {
        protected String name;

        Figure3D(String name) { this.name = name; }

        abstract double surfaceArea();
        abstract double volume();

        @Override
        public String toString() { return name; }
    }

    static class Cube extends Figure3D {
        private double a;

        Cube(double a) { super("Куб"); this.a = a; }

        @Override double surfaceArea() { return 6 * a * a; }
        @Override double volume() { return a * a * a; }

        @Override public String toString() { return super.toString() + "{a=" + a + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cube)) return false;
            return Double.compare(a, ((Cube) o).a) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a); }
    }

    static class Sphere extends Figure3D {
        private double r;

        Sphere(double r) { super("Сфера"); this.r = r; }

        @Override double surfaceArea() { return 4 * Math.PI * r * r; }
        @Override double volume() { return 4.0 / 3 * Math.PI * r * r * r; }

        @Override public String toString() { return super.toString() + "{r=" + r + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Sphere)) return false;
            return Double.compare(r, ((Sphere) o).r) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r); }
    }

    static class Cone extends Figure3D {
        private double r, h;

        Cone(double r, double h) { super("Конус"); this.r = r; this.h = h; }

        @Override double surfaceArea() { return Math.PI * r * (r + Math.sqrt(r * r + h * h)); }
        @Override double volume() { return Math.PI * r * r * h / 3; }

        @Override public String toString() { return super.toString() + "{r=" + r + ", h=" + h + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cone)) return false;
            Cone c = (Cone) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    static class Cylinder extends Figure3D {
        private double r, h;

        Cylinder(double r, double h) { super("Циліндр"); this.r = r; this.h = h; }

        @Override double surfaceArea() { return 2 * Math.PI * r * (r + h); }
        @Override double volume() { return Math.PI * r * r * h; }

        @Override public String toString() { return super.toString() + "{r=" + r + ", h=" + h + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cylinder)) return false;
            Cylinder c = (Cylinder) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    static class TriangularPyramid extends Figure3D {
        private double a, h;

        TriangularPyramid(double a, double h) { super("Трикутна піраміда"); this.a = a; this.h = h; }

        private double baseArea() { return Math.sqrt(3) / 4 * a * a; }

        @Override double surfaceArea() {
            double inR = a / (2 * Math.sqrt(3));
            double slant = Math.sqrt(h * h + inR * inR);
            return baseArea() + 3 * a * slant / 2;
        }
        @Override double volume() { return baseArea() * h / 3; }

        @Override public String toString() { return super.toString() + "{a=" + a + ", h=" + h + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TriangularPyramid)) return false;
            TriangularPyramid p = (TriangularPyramid) o;
            return Double.compare(a, p.a) == 0 && Double.compare(h, p.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a, h); }
    }

    static void run() {
        Figure3D[] figures = {
            new Cube(3), new Sphere(2), new Cone(2, 5),
            new Cylinder(2, 5), new TriangularPyramid(4, 6)
        };

        for (Figure3D f : figures) {
            System.out.println(f);
            System.out.printf("   Площа поверхні = %.3f%n", f.surfaceArea());
            System.out.printf("   Об'єм          = %.3f%n", f.volume());
        }

        System.out.println("\nПеревірка equals:");
        System.out.println("Cube(3) == Cube(3)?  " + new Cube(3).equals(new Cube(3)));
        System.out.println("Cube(3) == Cube(4)?  " + new Cube(3).equals(new Cube(4)));
        System.out.println("Cone(2,5) == Cylinder(2,5)?  " + new Cone(2, 5).equals(new Cylinder(2, 5)));
    }
}


class Task3 {
    interface Figure3D {
        double surfaceArea();
        double volume();
        String getName();
    }

    static class Cube implements Figure3D {
        private final String name = "Куб";
        private double a;

        Cube(double a) { this.a = a; }

        @Override public String getName() { return name; }
        @Override public double surfaceArea() { return 6 * a * a; }
        @Override public double volume() { return a * a * a; }

        @Override public String toString() { return name + "{a=" + a + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cube)) return false;
            return Double.compare(a, ((Cube) o).a) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a); }
    }

    static class Sphere implements Figure3D {
        private final String name = "Сфера";
        private double r;

        Sphere(double r) { this.r = r; }

        @Override public String getName() { return name; }
        @Override public double surfaceArea() { return 4 * Math.PI * r * r; }
        @Override public double volume() { return 4.0 / 3 * Math.PI * r * r * r; }

        @Override public String toString() { return name + "{r=" + r + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Sphere)) return false;
            return Double.compare(r, ((Sphere) o).r) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r); }
    }

    static class Cone implements Figure3D {
        private final String name = "Конус";
        private double r, h;

        Cone(double r, double h) { this.r = r; this.h = h; }

        @Override public String getName() { return name; }
        @Override public double surfaceArea() { return Math.PI * r * (r + Math.sqrt(r * r + h * h)); }
        @Override public double volume() { return Math.PI * r * r * h / 3; }

        @Override public String toString() { return name + "{r=" + r + ", h=" + h + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cone)) return false;
            Cone c = (Cone) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    static class Cylinder implements Figure3D {
        private final String name = "Циліндр";
        private double r, h;

        Cylinder(double r, double h) { this.r = r; this.h = h; }

        @Override public String getName() { return name; }
        @Override public double surfaceArea() { return 2 * Math.PI * r * (r + h); }
        @Override public double volume() { return Math.PI * r * r * h; }

        @Override public String toString() { return name + "{r=" + r + ", h=" + h + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cylinder)) return false;
            Cylinder c = (Cylinder) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    static class TriangularPyramid implements Figure3D {
        private final String name = "Трикутна піраміда";
        private double a, h;

        TriangularPyramid(double a, double h) { this.a = a; this.h = h; }

        private double baseArea() { return Math.sqrt(3) / 4 * a * a; }

        @Override public String getName() { return name; }
        @Override public double surfaceArea() {
            double inR = a / (2 * Math.sqrt(3));
            double slant = Math.sqrt(h * h + inR * inR);
            return baseArea() + 3 * a * slant / 2;
        }
        @Override public double volume() { return baseArea() * h / 3; }

        @Override public String toString() { return name + "{a=" + a + ", h=" + h + "}"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TriangularPyramid)) return false;
            TriangularPyramid p = (TriangularPyramid) o;
            return Double.compare(a, p.a) == 0 && Double.compare(h, p.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a, h); }
    }

    static void run() {
        Figure3D[] figures = {
            new Cube(3), new Sphere(2), new Cone(2, 5),
            new Cylinder(2, 5), new TriangularPyramid(4, 6)
        };

        for (Figure3D f : figures) {
            System.out.println(f);
            System.out.printf("   Площа поверхні = %.3f%n", f.surfaceArea());
            System.out.printf("   Об'єм          = %.3f%n", f.volume());
        }

        System.out.println("\nПеревірка equals:");
        System.out.println("Sphere(2) == Sphere(2)?  " + new Sphere(2).equals(new Sphere(2)));
        System.out.println("Sphere(2) == Sphere(3)?  " + new Sphere(2).equals(new Sphere(3)));
        System.out.println("Cylinder(2,5) == Cone(2,5)?  " + new Cylinder(2, 5).equals(new Cone(2, 5)));
    }
}


public class Main {
    static Scanner in = new Scanner(System.in);

    static String readString(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(in.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть число.");
            }
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("1. Завдання - Ієрархія кораблів");
            System.out.println("2. Завдання - Figure3D (абстрактний клас)");
            System.out.println("3. Завдання - Figure3D (інтерфейс)");
            System.out.println("0. Вихід");
            choice = readInt("Ваш вибір: ");

            switch (choice) {
                case 1: Task1.run(); break;
                case 2: Task2.run(); break;
                case 3: Task3.run(); break;
                case 0: System.out.println("До побачення!"); break;
                default: System.out.println("Невірний вибір.");
            }
        } while (choice != 0);

        in.close();
    }
}
