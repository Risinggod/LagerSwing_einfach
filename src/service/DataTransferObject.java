package service;

import java.sql.Timestamp;

import daoservice.LagerFXModel;

public class DataTransferObject extends LagerFXModel implements Comparable<DataTransferObject>, Cloneable{

    private Timestamp timestamp;

    public DataTransferObject(int id, int userId, String title, String body) {
        super();
        super.id = id;
        super.userId = userId;
        super.title = title;
        super.body = body;
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean setId(int id) {
        super.id = id;
        if (super.id == id)
            return true;

        return false;
    }

    public boolean setUserId(int userId) {
        super.userId = userId;
        if (super.userId == userId)
            return true;

        return false;
    }

    public boolean setTitle(String title) {
        super.title = title;
        if (super.title.equals(title))
            return true;

        return false;
    }

    public boolean setBody(String body) {
        super.body = body;
        if (super.body.equals(body))
            return true;

        return false;
    }

    @Override
    public int compareTo(DataTransferObject o) {
        return Integer.compare(this.getBody().length(), o.getBody().length());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (o == this)
            return true;

        if (o.getClass() != getClass())
            return false;

        DataTransferObject other = (DataTransferObject) o;

        if (this.timestamp != other.timestamp)
            return false;

        if (this.id != other.id)
            return false;

        if (this.userId != other.userId)
            return false;

        if (!this.getTitle().equals(other.getTitle()))
            return false;

        if (!this.getBody().equals(other.getBody()))
            return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = 31 + id + userId;
        result = 31 * result + getBody() == null ? 0 : getBody().hashCode();
        result = 31 * result + getTitle() == null ? 0 : getTitle().hashCode();
        return result;
    }

    @Override
    public DataTransferObject clone() {
        try {
            return (DataTransferObject) super.clone();
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", timestamp, id, userId, title, body);
    }
}