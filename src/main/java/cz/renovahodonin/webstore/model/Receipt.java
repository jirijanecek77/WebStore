package cz.renovahodonin.webstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Receipt
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Store store;

	private LocalDate date;
	private String number;

	public Receipt()
	{
	}

	public Receipt(LocalDate date, String number)
	{
		this.date = date;
		this.number = number;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Store getStore()
	{
		return store;
	}

	public void setStore(Store store)
	{
		this.store = store;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Receipt receipt = (Receipt) o;
		return Objects.equals(id, receipt.id) &&
				Objects.equals(store, receipt.store) &&
				Objects.equals(date, receipt.date) &&
				Objects.equals(number, receipt.number);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, store, date, number);
	}

	@Override
	public String toString()
	{
		return "Receipt{" +
				"id=" + id +
				", store=" + store +
				", date=" + date +
				", number='" + number + '\'' +
				'}';
	}
}
