package sparsity.arnaujia.logindebug;

import android.os.Parcel;
import android.os.Parcelable;

public class UserView implements Parcelable {
    private long id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String token;
    private boolean mandatoryPassChange;
    private String pathUri;


    public UserView() {}

    public UserView(long id, String name, String lastname, String username, String email, String token, boolean mandatoryPassChange, String pathUri) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.token = token;
        this.mandatoryPassChange = mandatoryPassChange;
        this.pathUri = pathUri;
    }

    protected UserView(Parcel in) {
        id = in.readLong();
        name = in.readString();
        lastname = in.readString();
        username = in.readString();
        email = in.readString();
        token = in.readString();
        mandatoryPassChange = in.readByte() != 0;
        pathUri = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(lastname);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(token);
        dest.writeByte((byte) (mandatoryPassChange ? 1 : 0));
        dest.writeString(pathUri);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserView> CREATOR = new Creator<UserView>() {
        @Override
        public UserView createFromParcel(Parcel in) {
            return new UserView(in);
        }

        @Override
        public UserView[] newArray(int size) {
            return new UserView[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isMandatoryPassChange() {
        return mandatoryPassChange;
    }

    public void setMandatoryPassChange(boolean mandatoryPassChange) {
        this.mandatoryPassChange = mandatoryPassChange;
    }

    public String getPathUri() {
        return pathUri;
    }

    public void setPathUri(String pathUri) {
        this.pathUri = pathUri;
    }

    public boolean hasImage() {
        return this.pathUri != null && !this.pathUri.trim().equals("");
    }
}

