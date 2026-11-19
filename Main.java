import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static String readStr(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine().trim());
    }

    static double readDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1 - Завдання 1 (ієрархія кораблів)");
            System.out.println("2 - Завдання 2 (Figure3D, абстрактний клас)");
            System.out.println("3 - Завдання 3 (Figure3D, інтерфейс)");
            System.out.println("0 - Вихід");
            int choice;
            try {
                choice = readInt("Ваш вибір: ");
            } catch (NumberFormatException e) {
                System.out.println("Введіть число від 0 до 3.");
                continue;
            }
            switch (choice) {
                case 1: Task1.run(); break;
                case 2: Task2.run(); break;
                case 3: Task3.run(); break;
                case 0: return;
                default: System.out.println("Невірний вибір.");
            }
        }
    }
}

// ===== Завдання 1 =====
class Task1 {
    // Базовий клас
    static class Ship {
        protected String name;
        protected int year;
        protected double displacement; // водотоннажність, т

        public Ship(String name, int year, double displacement) {
            this.name = name;
            this.year = year;
            this.displacement = displacement;
        }

        public void Show() {
            System.out.println("Корабель: " + name + ", рік побудови: " + year
                    + ", водотоннажність: " + displacement + " т");
        }
    }

    // Пароплав
    static class Steamship extends Ship {
        private double enginePower; // потужність машини, к.с.
        private String fuelType;

        public Steamship(String name, int year, double displacement, double enginePower, String fuelType) {
            super(name, year, displacement);
            this.enginePower = enginePower;
            this.fuelType = fuelType;
        }

        @Override
        public void Show() {
            System.out.println("Пароплав: " + name + ", рік: " + year + ", водотоннажність: " + displacement
                    + " т, потужність: " + enginePower + " к.с., паливо: " + fuelType);
        }
    }

    // Вітрильник
    static class Sailboat extends Ship {
        private int sailsCount;
        private double sailArea; // площа вітрил, м²

        public Sailboat(String name, int year, double displacement, int sailsCount, double sailArea) {
            super(name, year, displacement);
            this.sailsCount = sailsCount;
            this.sailArea = sailArea;
        }

        @Override
        public void Show() {
            System.out.println("Вітрильник: " + name + ", рік: " + year + ", водотоннажність: " + displacement
                    + " т, кількість вітрил: " + sailsCount + ", площа вітрил: " + sailArea + " м²");
        }
    }

    // Корвет
    static class Corvette extends Ship {
        private int gunsCount;
        private int crew;

        public Corvette(String name, int year, double displacement, int gunsCount, int crew) {
            super(name, year, displacement);
            this.gunsCount = gunsCount;
            this.crew = crew;
        }

        @Override
        public void Show() {
            System.out.println("Корвет: " + name + ", рік: " + year + ", водотоннажність: " + displacement
                    + " т, гармат: " + gunsCount + ", екіпаж: " + crew);
        }
    }

    static void run() {
        int n = Main.readInt("Скільки об'єктів ввести? ");
        Ship[] ships = new Ship[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Об'єкт " + (i + 1) + " ---");
            int type = Main.readInt("Тип (1-Корабель, 2-Пароплав, 3-Вітрильник, 4-Корвет): ");
            String name = Main.readStr("Назва: ");
            int year = Main.readInt("Рік побудови: ");
            double disp = Main.readDouble("Водотоннажність (т): ");

            switch (type) {
                case 2:
                    ships[i] = new Steamship(name, year, disp,
                            Main.readDouble("Потужність (к.с.): "), Main.readStr("Тип палива: "));
                    break;
                case 3:
                    ships[i] = new Sailboat(name, year, disp,
                            Main.readInt("Кількість вітрил: "), Main.readDouble("Площа вітрил (м²): "));
                    break;
                case 4:
                    ships[i] = new Corvette(name, year, disp,
                            Main.readInt("Кількість гармат: "), Main.readInt("Екіпаж: "));
                    break;
                default:
                    ships[i] = new Ship(name, year, disp);
            }
        }

        System.out.println("\n===== Введений масив =====");
        for (Ship s : ships) {
            s.Show();
        }
    }
}

// ===== Завдання 2: абстрактний клас =====
class Task2 {
    // Абстрактний базовий клас
    static abstract class Figure3D {
        protected final String name;

        protected Figure3D(String name) {
            this.name = name;
        }

        public abstract double surfaceArea();
        public abstract double volume();
    }

    static class Cube extends Figure3D {
        private final double a;

        public Cube(double a) { super("Куб"); this.a = a; }

        @Override public double surfaceArea() { return 6 * a * a; }
        @Override public double volume() { return a * a * a; }

        @Override public String toString() { return name + "[a=" + a + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Double.compare(a, ((Cube) o).a) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a); }
    }

    static class Sphere extends Figure3D {
        private final double r;

        public Sphere(double r) { super("Сфера"); this.r = r; }

        @Override public double surfaceArea() { return 4 * Math.PI * r * r; }
        @Override public double volume() { return 4.0 / 3 * Math.PI * r * r * r; }

        @Override public String toString() { return name + "[r=" + r + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Double.compare(r, ((Sphere) o).r) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r); }
    }

    static class Cone extends Figure3D {
        private final double r, h;

        public Cone(double r, double h) { super("Конус"); this.r = r; this.h = h; }

        @Override public double surfaceArea() {
            double l = Math.sqrt(r * r + h * h);
            return Math.PI * r * (r + l);
        }
        @Override public double volume() { return Math.PI * r * r * h / 3; }

        @Override public String toString() { return name + "[r=" + r + ", h=" + h + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cone c = (Cone) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    static class Cylinder extends Figure3D {
        private final double r, h;

        public Cylinder(double r, double h) { super("Циліндр"); this.r = r; this.h = h; }

        @Override public double surfaceArea() { return 2 * Math.PI * r * (r + h); }
        @Override public double volume() { return Math.PI * r * r * h; }

        @Override public String toString() { return name + "[r=" + r + ", h=" + h + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cylinder c = (Cylinder) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    // Правильна трикутна піраміда: a - сторона основи, h - висота піраміди
    static class TriangularPyramid extends Figure3D {
        private final double a, h;

        public TriangularPyramid(double a, double h) { super("Трикутна піраміда"); this.a = a; this.h = h; }

        private double baseArea() { return Math.sqrt(3) / 4 * a * a; }

        @Override public double surfaceArea() {
            double r = a / (2 * Math.sqrt(3));          // радіус вписаного кола основи
            double l = Math.sqrt(h * h + r * r);        // апофема
            return baseArea() + 3 * 0.5 * a * l;
        }
        @Override public double volume() { return baseArea() * h / 3; }

        @Override public String toString() { return name + "[a=" + a + ", h=" + h + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TriangularPyramid p = (TriangularPyramid) o;
            return Double.compare(a, p.a) == 0 && Double.compare(h, p.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a, h); }
    }

    static void run() {
        Figure3D[] figures = {
                new Cube(3),
                new Sphere(2),
                new Cone(2, 5),
                new Cylinder(2, 5),
                new TriangularPyramid(4, 6)
        };

        for (Figure3D f : figures) {
            System.out.println(f);
            System.out.printf("  Площа поверхні: %.3f%n", f.surfaceArea());
            System.out.printf("  Об'єм: %.3f%n", f.volume());
        }

        System.out.println("\nПеревірка equals:");
        System.out.println("Cube(3).equals(Cube(3)): " + new Cube(3).equals(new Cube(3)));
        System.out.println("Cube(3).equals(Cube(4)): " + new Cube(3).equals(new Cube(4)));
        System.out.println("Cone(2,5).equals(Cylinder(2,5)): " + new Cone(2, 5).equals(new Cylinder(2, 5)));
        System.out.println("figures[0].equals(figures[0]): " + figures[0].equals(figures[0]));
    }
}

// ===== Завдання 3: інтерфейс =====
class Task3 {
    // Інтерфейс замість абстрактного класу
    static interface Figure3D {
        double surfaceArea();
        double volume();
    }

    static class Cube implements Figure3D {
        private final String name;
        private final double a;

        public Cube(double a) { this.name = "Куб"; this.a = a; }

        @Override public double surfaceArea() { return 6 * a * a; }
        @Override public double volume() { return a * a * a; }

        @Override public String toString() { return name + "[a=" + a + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Double.compare(a, ((Cube) o).a) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a); }
    }

    static class Sphere implements Figure3D {
        private final String name;
        private final double r;

        public Sphere(double r) { this.name = "Сфера"; this.r = r; }

        @Override public double surfaceArea() { return 4 * Math.PI * r * r; }
        @Override public double volume() { return 4.0 / 3 * Math.PI * r * r * r; }

        @Override public String toString() { return name + "[r=" + r + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Double.compare(r, ((Sphere) o).r) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r); }
    }

    static class Cone implements Figure3D {
        private final String name;
        private final double r, h;

        public Cone(double r, double h) { this.name = "Конус"; this.r = r; this.h = h; }

        @Override public double surfaceArea() {
            double l = Math.sqrt(r * r + h * h);
            return Math.PI * r * (r + l);
        }
        @Override public double volume() { return Math.PI * r * r * h / 3; }

        @Override public String toString() { return name + "[r=" + r + ", h=" + h + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cone c = (Cone) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    static class Cylinder implements Figure3D {
        private final String name;
        private final double r, h;

        public Cylinder(double r, double h) { this.name = "Циліндр"; this.r = r; this.h = h; }

        @Override public double surfaceArea() { return 2 * Math.PI * r * (r + h); }
        @Override public double volume() { return Math.PI * r * r * h; }

        @Override public String toString() { return name + "[r=" + r + ", h=" + h + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cylinder c = (Cylinder) o;
            return Double.compare(r, c.r) == 0 && Double.compare(h, c.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(r, h); }
    }

    // Правильна трикутна піраміда: a - сторона основи, h - висота піраміди
    static class TriangularPyramid implements Figure3D {
        private final String name;
        private final double a, h;

        public TriangularPyramid(double a, double h) { this.name = "Трикутна піраміда"; this.a = a; this.h = h; }

        private double baseArea() { return Math.sqrt(3) / 4 * a * a; }

        @Override public double surfaceArea() {
            double r = a / (2 * Math.sqrt(3));          // радіус вписаного кола основи
            double l = Math.sqrt(h * h + r * r);        // апофема
            return baseArea() + 3 * 0.5 * a * l;
        }
        @Override public double volume() { return baseArea() * h / 3; }

        @Override public String toString() { return name + "[a=" + a + ", h=" + h + "]"; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TriangularPyramid p = (TriangularPyramid) o;
            return Double.compare(a, p.a) == 0 && Double.compare(h, p.h) == 0;
        }
        @Override public int hashCode() { return Objects.hash(a, h); }
    }

    static void run() {
        Figure3D[] figures = {
                new Cube(3),
                new Sphere(2),
                new Cone(2, 5),
                new Cylinder(2, 5),
                new TriangularPyramid(4, 6)
        };

        for (Figure3D f : figures) {
            System.out.println(f);
            System.out.printf("  Площа поверхні: %.3f%n", f.surfaceArea());
            System.out.printf("  Об'єм: %.3f%n", f.volume());
        }

        System.out.println("\nПеревірка equals:");
        System.out.println("Cube(3).equals(Cube(3)): " + new Cube(3).equals(new Cube(3)));
        System.out.println("Cube(3).equals(Cube(4)): " + new Cube(3).equals(new Cube(4)));
        System.out.println("Cone(2,5).equals(Cylinder(2,5)): " + new Cone(2, 5).equals(new Cylinder(2, 5)));
        System.out.println("figures[0].equals(figures[0]): " + figures[0].equals(figures[0]));
    }
}
