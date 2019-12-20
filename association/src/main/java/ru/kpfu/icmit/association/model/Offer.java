package ru.kpfu.icmit.association.model;

import org.hibernate.annotations.Type;
import ru.kpfu.icmit.association.model.soap.Content;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Предложение товара/ресурса
 */
@Entity
public class Offer extends Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contractIdGenerator")
    @SequenceGenerator(name = "offerIdGenerator", sequenceName = "offer_seq", allocationSize=1)
    Long id;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private Nomenclature nomenclature;

    private double priceOfProduct;

    private int countOfProduct;

    private String unitCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Temporal(TemporalType.DATE)
    private Date dateOfPerformance;

    @Type(type="pg-uuid")
    private UUID uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(int countOfProduct) {
        this.countOfProduct = countOfProduct;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getDateOfPerformance() {
        return dateOfPerformance;
    }

    public void setDateOfPerformance(Date dateOfPerformance) {
        this.dateOfPerformance = dateOfPerformance;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }
}
