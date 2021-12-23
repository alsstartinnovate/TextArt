package com.anilax.customdesign.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        public User[] newArray(int i) {
            return new User[i];
        }
    };
    @SerializedName("badge")
    @Expose
    private Badge badge;
    @SerializedName("bio")
    @Expose
    private final String bio;
    @SerializedName("current_user_collections")
    @Expose
    private List<CurrentUserCollection> currentUserCollections = null;
    @SerializedName("downloads")
    @Expose
    private Integer downloads;
    @SerializedName("first_name")
    @Expose
    private final String firstName;
    @SerializedName("followed_by_user")
    @Expose
    private Boolean followedByUser;
    @SerializedName("followers_count")
    @Expose
    private final Integer followersCount;
    @SerializedName("following_count")
    @Expose
    private final Integer followingCount;
    @SerializedName("id")
    @Expose


    private String id;
    @SerializedName("instagram_username")
    @Expose
    private final String instagramUsername;
    @SerializedName("last_name")
    @Expose
    private final String lastName;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("portfolio_url")
    @Expose
    private final Object portfolioUrl;
    @SerializedName("profile_image")
    @Expose
    private final ProfileImage profileImage;
    @SerializedName("total_collections")
    @Expose
    private Integer totalCollections;
    @SerializedName("total_likes")
    @Expose
    private final Integer totalLikes;
    @SerializedName("total_photos")
    @Expose
    private final Integer totalPhotos;
    @SerializedName("twitter_username")
    @Expose
    private final String twitterUsername;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("username")
    @Expose
    private final String username;

    public int describeContents() {
        return 0;
    }

    protected User(Parcel parcel) {
        this.id = (String) parcel.readValue(String.class.getClassLoader());
        this.updatedAt = (String) parcel.readValue(String.class.getClassLoader());
        this.username = (String) parcel.readValue(String.class.getClassLoader());
        this.name = (String) parcel.readValue(String.class.getClassLoader());
        this.firstName = (String) parcel.readValue(String.class.getClassLoader());
        this.lastName = (String) parcel.readValue(String.class.getClassLoader());
        this.instagramUsername = (String) parcel.readValue(String.class.getClassLoader());
        this.twitterUsername = (String) parcel.readValue(String.class.getClassLoader());
        this.portfolioUrl = parcel.readValue(Object.class.getClassLoader());
        this.bio = (String) parcel.readValue(String.class.getClassLoader());
        this.location = (String) parcel.readValue(String.class.getClassLoader());
        this.totalLikes = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.totalPhotos = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.totalCollections = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.followedByUser = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.followersCount = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.followingCount = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.downloads = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.profileImage = (ProfileImage) parcel.readValue(ProfileImage.class.getClassLoader());
        this.badge = (Badge) parcel.readValue(Badge.class.getClassLoader());
        this.links = (Links) parcel.readValue(Links.class.getClassLoader());
    }


    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public String getUsername() {
        return this.username;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }


    public String getLastName() {
        return this.lastName;
    }


    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }


    public Integer getTotalCollections() {
        return this.totalCollections;
    }

    public void setTotalCollections(Integer num) {
        this.totalCollections = num;
    }

    public Boolean getFollowedByUser() {
        return this.followedByUser;
    }

    public void setFollowedByUser(Boolean bool) {
        this.followedByUser = bool;
    }


    public Integer getDownloads() {
        return this.downloads;
    }

    public void setDownloads(Integer num) {
        this.downloads = num;
    }


    public void setBadge(Badge badge2) {
        this.badge = badge2;
    }

    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }

    public List<CurrentUserCollection> getCurrentUserCollections() {
        return this.currentUserCollections;
    }

    public void setCurrentUserCollections(List<CurrentUserCollection> list) {
        this.currentUserCollections = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.updatedAt);
        parcel.writeValue(this.username);
        parcel.writeValue(this.name);
        parcel.writeValue(this.firstName);
        parcel.writeValue(this.lastName);
        parcel.writeValue(this.instagramUsername);
        parcel.writeValue(this.twitterUsername);
        parcel.writeValue(this.portfolioUrl);
        parcel.writeValue(this.bio);
        parcel.writeValue(this.location);
        parcel.writeValue(this.totalLikes);
        parcel.writeValue(this.totalPhotos);
        parcel.writeValue(this.totalCollections);
        parcel.writeValue(this.followedByUser);
        parcel.writeValue(this.followersCount);
        parcel.writeValue(this.followingCount);
        parcel.writeValue(this.downloads);
        parcel.writeValue(this.profileImage);
        parcel.writeValue(this.badge);
        parcel.writeValue(this.links);
        parcel.writeList(this.currentUserCollections);
    }
}
