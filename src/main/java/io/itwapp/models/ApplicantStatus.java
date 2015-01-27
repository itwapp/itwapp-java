package io.itwapp.models;


import com.google.gson.annotations.SerializedName;

/**
 * Enumeration of applicant status
 */
public enum ApplicantStatus {

    @SerializedName("3")
    COMPLETED(3),

    @SerializedName("2")
    INPROGRESS(2),

    @SerializedName("1")
    OPENEMAIL(1),

    @SerializedName("0")
    EMAILSENT(0),

    @SerializedName("-1")
    UNKNOWN(-1);

    /**
     * The default status.
     */
    public static final ApplicantStatus DEFAULT_CODE = UNKNOWN;

    private final int code;

    private ApplicantStatus(int code)    {
        this.code = code;
    }

    /**
     * The code of this status.
     */
    public int getCode()    {
        return code;
    }

    /**
     * Returns a region enum corresponding to the given region name.
     *
     * @param code The code of the status. Ex.: 2
     * @return ApplicantStatus enum representing the code.
     */
    public static ApplicantStatus fromCode(int code) throws IllegalArgumentException{
        for (ApplicantStatus region : ApplicantStatus.values()) {
            if (code  == region.getCode()) {
                return region;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + code + " value!");
    }

}
