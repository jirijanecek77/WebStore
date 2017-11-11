package cz.renovahodonin.webstore.model;

import java.util.Arrays;

public enum ItemUnit
{
    KS(1, "kus"),
    M(2, "bm"),
    M2(3, "m2"),
    M3(4, "m3"),
    TUN(5, "t"),
    KG(6, "kg");

    public int getId()
    {
        return id;
    }

    private final int id;
    private final String sign;

    ItemUnit(int id, String sign)
    {
        this.id = id;
        this.sign = sign;
    }

    public static ItemUnit getByID(int id)
    {
        return Arrays.stream(values())
                .filter(itemUnit -> itemUnit.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("Hodnota " + id + " není jednotka skladové položky!"));
    }
}
