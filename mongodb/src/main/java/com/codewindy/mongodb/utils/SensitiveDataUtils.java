package com.codewindy.mongodb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensitiveDataUtils {
    private static boolean hideFlag = true;
    public static final int BANKCARDNO_DATA = 0;
    public static final int IDCARDNO_DATA = 1;
    public static final int PHONENO_DATA = 2;
    public static final int EMAIL_DATA = 3;
    private static final String ID_CARD_REGEXP = "[0-9]{15}|[0-9]{18}|[0-9]{14}X|[0-9]{17}X";
    private static final String BANK_CARD_REGEXP = "[0-9]{8,19}";
    private static final String MAINLAND_PHONE_TEL_REGEXP = "[0-9]{3,4}[-]?[0-9]{7,8}";
    private static final Pattern ID_CARD_PATTERN = Pattern.compile("[0-9]{15}|[0-9]{18}|[0-9]{14}X|[0-9]{17}X");
    private static final Pattern BANK_CARD_PATTERN = Pattern.compile("[0-9]{8,19}");
    private static final Pattern MAINLAND_PHONE_TEL_PATTERN = Pattern.compile("[0-9]{3,4}[-]?[0-9]{7,8}");

    public SensitiveDataUtils(boolean hideFlag) {
        setHideFlag(hideFlag);
    }

    public SensitiveDataUtils() {
        setHideFlag(true);
    }

    private static boolean isEmail(String email) {
        return email.indexOf(64) > 0;
    }

    public static String alipayLogonIdHide(String logonId) {
        if (!needHide()) {
            return logonId;
        } else if (isBlank(logonId)) {
            return logonId;
        } else {
            return isEmail(logonId) ? emailHide(logonId) : cellphoneHide(logonId);
        }
    }

    public static String alipayLogonIdHideSMS(String logonId) {
        if (!needHide()) {
            return logonId;
        } else if (isBlank(logonId)) {
            return logonId;
        } else {
            return isEmail(logonId) ? emailHideSMS(logonId) : cellphoneHideSMS(logonId);
        }
    }

    public static String nameHide(String name) {
        if (!needHide()) {
            return name;
        } else if (isBlank(name)) {
            return name;
        } else if (name.length() <= 3) {
            return customizeHide(name, 0, name.length() - 1, 1);
        } else {
            return name.length() > 3 && name.length() <= 6 ? customizeHide(name, 0, 2, name.length() - 2) : customizeHide(name, 1, 2, name.length() - 3);
        }
    }

    public static String idCardNoHide(String idCardNo, boolean doValidate) {
        if (!needHide()) {
            return idCardNo;
        } else if (isBlank(idCardNo)) {
            return idCardNo;
        } else {
            return doValidate && !isIdCardNo(idCardNo) ? defaultHide(idCardNo) : customizeHide(idCardNo, 1, 1, idCardNo.length() - 2);
        }
    }

    public static String idCardNoHide(String idCardNo) {
        return idCardNoHide(idCardNo, false);
    }

    public static String bankCardNoHide(String bankCardNo, boolean doValidate) {
        if (!needHide()) {
            return bankCardNo;
        } else if (isBlank(bankCardNo)) {
            return bankCardNo;
        } else if (doValidate && !isBankCardNo(bankCardNo)) {
            return defaultHide(bankCardNo);
        } else {
            return bankCardNo.length() >= 16 ? customizeHide(bankCardNo, 6, 4, bankCardNo.length() - 10) : defaultHide(bankCardNo);
        }
    }

    public static String bankCardNoHide(String bankCardNo) {
        return bankCardNoHide(bankCardNo, false);
    }

    public static String phoneOrTelNoHide(String phoneOrTelNo, boolean doValidate) {
        if (!needHide()) {
            return phoneOrTelNo;
        } else if (isBlank(phoneOrTelNo)) {
            return phoneOrTelNo;
        } else {
            String tmp = phoneOrTelNo.trim();
            if (doValidate && !isPhoneOrTelNo(tmp)) {
                return defaultHide(tmp);
            } else if (tmp.charAt(0) == '1') {
                return customizeHide(tmp, 3, 2, tmp.length() - 5);
            } else if (tmp.indexOf("-") > 0) {
                int frontCharNum = tmp.indexOf(45) + 1;
                return customizeHide(tmp, frontCharNum, 4, tmp.length() - 4 - frontCharNum);
            } else {
                return defaultHide(tmp);
            }
        }
    }

    public static String phoneOrTelNoHide(String phoneOrTelNo) {
        return phoneOrTelNoHide(phoneOrTelNo, false);
    }

    public static String cellphoneHide(String cellphone) {
        if (!needHide()) {
            return cellphone;
        } else if (isBlank(cellphone)) {
            return cellphone;
        } else {
            String tmp = cellphone.trim();
            if (cellphone.length() == 11) {
                return customizeHide(tmp, 3, 2, 6);
            } else {
                int notHideNum = tmp.length() - 4;
                return customizeHide(tmp, notHideNum / 2, notHideNum - notHideNum / 2, 4);
            }
        }
    }

    public static String cellphoneHideSMS(String cellphone) {
        if (!needHide()) {
            return cellphone;
        } else if (isBlank(cellphone)) {
            return cellphone;
        } else {
            String tmp = cellphone.trim();
            if (cellphone.length() == 11) {
                return customizeHide(tmp, 3, 2, 1);
            } else {
                int notHideNum = tmp.length() - 4;
                return customizeHide(tmp, notHideNum / 2, notHideNum - notHideNum / 2, 1);
            }
        }
    }

    public static String emailHide(String email, boolean doValidate) {
        if (!needHide()) {
            return email;
        } else if (isBlank(email)) {
            return email;
        } else if (doValidate && !isEmail(email)) {
            return defaultHide(email);
        } else {
            String tmp = email.trim();
            int atPos = tmp.indexOf(64);
            int frontNum = atPos < 3 ? atPos : 3;
            return customizeHide(tmp, frontNum, tmp.length() - atPos, 3);
        }
    }

    public static String emailHide(String email) {
        return emailHide(email, false);
    }

    public static String emailHideSMS(String email, boolean doValidate) {
        if (!needHide()) {
            return email;
        } else if (isBlank(email)) {
            return email;
        } else if (doValidate && !isEmail(email)) {
            return defaultHide(email);
        } else {
            StringBuilder result = new StringBuilder();
            String tmp = email.trim();
            int atPos = tmp.indexOf(64);
            int frontNum = atPos < 3 ? atPos : 3;
            result.append(tmp.substring(0, frontNum));
            result.append("*@");
            String backStr = tmp.substring(atPos + 1);
            int dotIndex = backStr.indexOf(46);
            if (dotIndex <= 7) {
                result.append(backStr.substring(0, dotIndex) + ".*");
            } else {
                result.append(backStr.substring(0, 7) + "*");
            }

            return result.toString();
        }
    }

    public static String emailHideSMS(String email) {
        return emailHideSMS(email, false);
    }

    public static String filterHide(String sourceStr, String tagBegin, String tagEnd, int sensitiveDataType) {
        if (!needHide()) {
            return sourceStr;
        } else if (isBlank(sourceStr)) {
            return sourceStr;
        } else {
            StringBuilder tmp = new StringBuilder(sourceStr);
            StringBuilder target = new StringBuilder();
            int begin = tmp.indexOf(tagBegin);

            for(int end = tmp.indexOf(tagEnd); begin != -1 && end != -1; end = tmp.indexOf(tagEnd)) {
                target = target.append(tmp.toString().toCharArray(), 0, begin + tagBegin.length());
                String coverReplace = "";
                switch(sensitiveDataType) {
                    case 0:
                        coverReplace = bankCardNoHide(tmp.substring(begin + tagBegin.length(), end));
                        break;
                    case 1:
                        coverReplace = idCardNoHide(tmp.substring(begin + tagBegin.length(), end));
                        break;
                    case 2:
                        coverReplace = phoneOrTelNoHide(tmp.substring(begin + tagBegin.length(), end));
                        break;
                    case 3:
                        coverReplace = emailHide(tmp.substring(begin + tagBegin.length(), end));
                        break;
                    default:
                        coverReplace = defaultHide(tmp.substring(begin + tagBegin.length(), end));
                }

                target.append(coverReplace);
                target.append(tagEnd);
                tmp = new StringBuilder(tmp.substring(end + tagEnd.length()));
                begin = tmp.indexOf(tagBegin);
            }

            target.append(tmp);
            return target.toString();
        }
    }

    public static String taobaoNickHide(String sensitiveData) {
        if (!needHide()) {
            return sensitiveData;
        } else if (isBlank(sensitiveData)) {
            return sensitiveData;
        } else {
            String tmp = sensitiveData.trim();
            return customizeHide(tmp, 1, 1, 2);
        }
    }

    public static String defaultHide(String sensitiveData) {
        if (!needHide()) {
            return sensitiveData;
        } else if (isBlank(sensitiveData)) {
            return sensitiveData;
        } else {
            String tmp = sensitiveData.trim();
            int length = tmp.length();
            int headNum = (int)Math.ceil((double)(length * 1) / 3.0D);
            int tailNum = (int)Math.floor((double)(length * 1) / 3.0D);
            return customizeHide(tmp, headNum, tailNum, length - headNum - tailNum);
        }
    }

    public static String customizeHide(String sensitiveData, int frontCharNum, int tailCharNum, int hiddenCharNum) {
        if (isBlank(sensitiveData)) {
            return sensitiveData;
        } else {
            String tmp = sensitiveData.trim();
            int length = tmp.length();
            if (frontCharNum >= 0 && tailCharNum >= 0 && hiddenCharNum >= 0 && frontCharNum + tailCharNum <= length) {
                int beginIndex = frontCharNum - 1;
                int endIndex = length - tailCharNum;
                StringBuilder result = new StringBuilder();
                if (beginIndex >= 0 && beginIndex < length) {
                    result.append(tmp.substring(0, frontCharNum));
                }

                for(int i = 0; i < hiddenCharNum; ++i) {
                    result.append('*');
                }

                if (endIndex >= 0 && endIndex < length) {
                    result.append(tmp.substring(endIndex));
                }

                return result.toString();
            } else {
                return tmp;
            }
        }
    }

    private static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    private static boolean isIdCardNo(String idCardNo) {
        if (isBlank(idCardNo)) {
            return false;
        } else {
            Matcher matcher = ID_CARD_PATTERN.matcher(idCardNo.trim());
            return matcher.matches();
        }
    }

    private static boolean isBankCardNo(String bankCardNo) {
        if (isBlank(bankCardNo)) {
            return false;
        } else {
            Matcher matcher = BANK_CARD_PATTERN.matcher(bankCardNo.trim());
            return matcher.matches();
        }
    }

    private static boolean isPhoneOrTelNo(String phoneOrTelNo) {
        if (isBlank(phoneOrTelNo)) {
            return false;
        } else {
            Matcher matcher = MAINLAND_PHONE_TEL_PATTERN.matcher(phoneOrTelNo.trim());
            return matcher.matches();
        }
    }

    public static void setHideFlag(boolean hideFlag) {
        hideFlag = hideFlag;
    }

    public static boolean needHide() {
        return hideFlag;
    }

    public static void main(String[] args) {
        System.out.println("cellphoneHide = " + cellphoneHide("13714156452"));
        System.out.println("cellphoneHide = " + cellphoneHide("8790071998"));
    }
}
