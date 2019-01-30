package jp.co.tis.tiscon4.form;

import jp.co.tis.tiscon4.common.code.JobType;
import jp.co.tis.tiscon4.common.code.TreatedType;
import nablarch.core.util.StringUtil;
import nablarch.core.validation.ee.Domain;
import nablarch.core.validation.ee.Required;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 漢字氏名 */
    @Required
    @Domain("kanjiName")
    private String kanjiName1;
    private String kanjiName2;

    /** カナ氏名 */
    @Required
    @Domain("kanaName")
    private String kanaName1;
    private String kanaName2;

    /** 英字氏名 */
    @Required
    @Domain("alphabetName")
    private String alphabetName1;
    private String alphabetName2;

    /** 性別 */
    @Required(message = "{tiscon4.order.inputUser.error.select}")
    @Domain("gender")
    private String gender;

    /** 生年月日 */
    @Required
    @Domain("dateOfBirth")
    private String dateOfBirth;

    /** 郵便番号 */
    @Required
    @Domain("zipCode")
    private String zipCode;

    /** 住所 */
    @Required
    @Domain("address")
    private String address;

    /** 自宅電話番号 */
    @Required
    @Domain("homePhoneNumber")
    private String homePhoneNumber;

    /** 携帯電話番号 */
    @Required
    @Domain("mobilePhoneNumber")
    private String mobilePhoneNumber;

    /** メールアドレス */
    @Required
    @Domain("emailAddress")
    private String emailAddress;

    /** 配偶者有無 */
    @Required(message = "{tiscon4.order.inputUser.error.select}")
    @Domain("married")
    private String married;

    /** 所得金額 */
    @Required
    @Domain("income")
    private String income;

    /** 職業 */
    @Required(message = "{tiscon4.order.inputUser.error.select}")
    @Domain("job")
    private String job;

    /** その他の職業 */
    @Domain("otherJob")
    private String otherJob;

    /** 治療歴有無 */
    @Required(message = "{tiscon4.order.inputUser.error.select}")
    @Domain("treated")
    private String treated;

    /** 既往歴 */
    @Domain("medicalHistory")
    private String medicalHistory;

    public String getKanjiName1() {
        return kanjiName1;
    }
    public String getKanjiName2() {
        return kanjiName2;
    }

    public void setKanjiName1(String kanjiName1) { this.kanjiName1 = kanjiName1; }
    public void setKanjiName2(String kanjiName2) {
        this.kanjiName2 = kanjiName2;
    }

    public String getKanaName1() {
        return kanaName1;
    }
    public String getKanaName2() {
        return kanaName2;
    }

    public void setKanaName1(String kanaName1) {
        this.kanaName1 = kanaName1;
    }
    public void setKanaName2(String kanaName2) {
        this.kanaName2 = kanaName2;
    }

    public String getAlphabetName1() {
        return alphabetName1;
    }
    public String getAlphabetName2() {
        return alphabetName2;
    }

    public void setAlphabetName1(String alphabetName1) {
        this.alphabetName1 = alphabetName1;
    }
    public void setAlphabetName2(String alphabetName2) {
        this.alphabetName2 = alphabetName2;
    }

    public String getdateOfBirth() {return dateOfBirth;}

    public void setdateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getHomePhoneNumber() { return homePhoneNumber; }

    public void setHomePhoneNumber(String homePhoneNumber) { this.homePhoneNumber = homePhoneNumber; }

    public String getMobilePhoneNumber() { return mobilePhoneNumber; }

    public void setMobilePhoneNumber(String mobilePhoneNumber) { this.mobilePhoneNumber = mobilePhoneNumber; }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getMarried() { return married; }

    public void setMarried(String married) { this.married = married; }

    public String getJob() { return job; }

    public void setJob(String job) { this.job = job; }

    public String getIncome() { return income; }

    public void setIncome(String income) { this.income = income; }

    public String getOtherJob() { return otherJob; }

    public void setOtherJob(String otherJob) { this.otherJob = otherJob; }

    public String getTreated() { return treated; }

    public void setTreated(String treated) { this.treated = treated; }

    public String getMedicalHistory() { return medicalHistory; }

    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }

    /**
     * その他の職業欄が正しく入力されているかどうか判定する。
     * 職業で「その他（有職）」を選択し、その他の職業欄に何らかの値が入力された場合、正しく入力されたと判定する。
     *
     * @return その他の職業欄に入力がある場合にtrue
     */
    @AssertTrue(message = "{tiscon4.order.inputUser.error.hasValueOtherJob}")
    public boolean hasValueOtherJob() {
        if (StringUtil.isNullOrEmpty(job)) {
            // 職業が未入力の場合は、相関バリデーションは実施しない。(バリデーションOKとする)
            return true;
        } else if (JobType.EMPLOYED.getCode().equals(job) && StringUtil.isNullOrEmpty(otherJob)) {
            return false;
        }
        return true;
    }

    /**
     * 既往歴が正しく入力されているかどうか判定する。
     * 治療有無で「はい」を選択し、既往歴に何らかの値が入力された場合、正しく入力されたと判定する。
     *
     * @return 既往歴に入力がある場合にtrue
     */
    @AssertTrue(message = "{tiscon4.order.inputUser.error.hasValueMedicalHistory}")
    public boolean hasValueMedicalHistory() {
        if (StringUtil.isNullOrEmpty(treated)) {
            // 治療有無が未入力の場合は、相関バリデーションは実施しない。(バリデーションOKとする)
            return true;
        } else if (TreatedType.TREATED.getCode().equals(treated) && StringUtil.isNullOrEmpty(medicalHistory)) {
            return false;
        }
        return true;
    }

}
