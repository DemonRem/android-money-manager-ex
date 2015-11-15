package com.money.manager.ex.domainmodel;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Parcel;
import android.os.Parcelable;

import com.money.manager.ex.Constants;
import com.money.manager.ex.database.ITransactionEntity;

import org.apache.commons.lang3.StringUtils;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Split Category for checking account transaction.
 */
public class SplitCategory
    extends EntityBase
    implements ITransactionEntity {

    public static String TABLE_NAME = "SPLITTRANSACTIONS_V1";

    public static final String SPLITTRANSID = "SPLITTRANSID";
    public static final String TRANSID = "TRANSID";
    public static final String CATEGID = "CATEGID";
    public static final String SUBCATEGID = "SUBCATEGID";
    public static final String SPLITTRANSAMOUNT = "SPLITTRANSAMOUNT";

    public final static Parcelable.Creator<SplitCategory> CREATOR = new Parcelable.Creator<SplitCategory>() {
        public SplitCategory createFromParcel(Parcel source) {
            SplitCategory split = new SplitCategory();
            split.readFromParcel(source);
            return split;
        }

        @Override
        public SplitCategory[] newArray(int size) {
            return new SplitCategory[size];
        }
    };

    public static SplitCategory create(int transactionId, int categoryId, int subcategoryId,
                                             double amount) {
        SplitCategory entity = new SplitCategory();

        entity.setId(Constants.NOT_SET);
        entity.setCategoryId(categoryId);
        entity.setSubcategoryId(subcategoryId);
        entity.setAmount(MoneyFactory.fromDouble(amount));
        entity.setTransId(transactionId);

        return entity;
    }

    public Integer getId() {
        return getInt(SPLITTRANSID);
    }

    public void setId(int value) {
        setInteger(SPLITTRANSID, value);
    }

    @Override
    public Integer getCategoryId() {
        return getInt(CATEGID);
    }

    @Override
    public Money getAmount() {
        return getMoney(SPLITTRANSAMOUNT);
    }

    @Override
    public Integer getSubcategoryId() {
        return getInt(SUBCATEGID);
    }

    @Override
    public void setCategoryId(int categId) {
        setInteger(CATEGID, categId);
    }

    @Override
    public void setAmount(Money splitTransAmount) {
        setMoney(SPLITTRANSAMOUNT, splitTransAmount);
    }

    @Override
    public void setSubcategoryId(int subCategId) {
        setInteger(SUBCATEGID, subCategId);
    }

    @Override
    public void loadFromCursor(Cursor c) {
        super.loadFromCursor(c);

        DatabaseUtils.cursorDoubleToContentValuesIfPresent(c, contentValues, SPLITTRANSAMOUNT);
    }

    public int getTransId() {
        return getInt(TRANSID);
    }

    public void setTransId(int value) {
        setInteger(TRANSID, value);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int id = getId() == null ? Constants.NOT_SET : getId();
        dest.writeInt(id);
        dest.writeInt(getTransId());
        dest.writeInt(getCategoryId());
        dest.writeInt(getSubcategoryId());
        dest.writeString(getAmount().toString());
    }

    public void readFromParcel(Parcel source) {
        setId(source.readInt());
        setTransId(source.readInt());
        setCategoryId(source.readInt());
        setSubcategoryId(source.readInt());
        String amount = source.readString();
        if (StringUtils.isNotEmpty(amount)) {
            setAmount(MoneyFactory.fromString(amount));
        }
    }

}