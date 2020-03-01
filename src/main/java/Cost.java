import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;


public class Cost {
    private long id;  //идентификатор в БД (заменить на serial primary key not null)
    private String product_code; //код товара
    private int number; // номер цены
    private int depart; // номер отдела
    private LocalDateTime begin; // начало действия (заменить на LocalDateTime)
    private LocalDateTime end; // конец действия
    private long value; // значение цены в копейках

    public Cost (long id, String product_code, int number, int depart, LocalDateTime begin, LocalDateTime end, long value) {
        this.id = id;
        this.product_code = product_code;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public Cost() {
    }

    public Cost(Cost other) {
        this(other.getId(), other.getProduct_code(), other.getNumber(), other.getDepart(),
                other.getBegin(), other.getEnd(), other.getValue());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
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
        return "Cost {" + product_code + ", " + number + ", " + depart + ", " + begin + ", " + end + ", " + value + '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cost cost = (Cost) o;
        return Objects.equals(product_code, cost.product_code)
                && Objects.equals(number, cost.number)
                && Objects.equals(depart, cost.depart)
                && Objects.equals(begin, cost.begin)
                && Objects.equals(end, cost.end)
                && Objects.equals(value, cost.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_code);
    }
}
