import java.util.*;

public class Overlap {
    public static List<Product> overlap(List<Product> now, List<Product> discount) {
        Product n = now.get(0);
        Product d = discount.get(0);
        return List.of(
                new Product(n.getBegin(), d.getBegin(), n.getValue()),
                d,
                new Product(d.getEnd(), n.getEnd(), n.getValue())
        );
    }

    public static class Product {
        int begin;
        int end;
        long value;

        public Product(int begin, int end, long value) {
            this.begin = begin;
            this.end = end;
            this.value = value;
        }

        public int getBegin() {
            return begin;
        }

        public void setBegin(int begin) {
            this.begin = begin;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Product {" + begin + ", " + end + ", " + value + '}' + "\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Product product = (Product) o;
            return  Objects.equals(begin, product.begin)
                    && Objects.equals(end, product.end)
                    && Objects.equals(value, product.value);
        }
    }
}