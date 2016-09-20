package boymustafa.com.myapplication;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * Created by Boy on 16/09/2016.
 */
public  class Utils {


    /*check if phone number is valid from spesific country
    countryCode could be found at https://www.iso.org/obp/ui/#search/code/, 2-letters
     */
    public  static boolean isValidPhoneNumber(String phoneNumber, String countryCode){
        //NOTE: This should probably be a member variable.
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try
        {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, countryCode);
            return phoneUtil.isValidNumber(numberProto);
        }
        catch (NumberParseException e)
        {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }

        return false;
    }


}
