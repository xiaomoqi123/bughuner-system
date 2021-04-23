package bughunter.bughunterserver.constants;

/**
 * @author sean
 * @date 2019-03-08.
 */
public class ConverterMessage {

    public static String convertMessage(String message) {
        if (message.equals("Click Home button because has tried more than 3 times")) {
            return "Press HOME button";
        } else if (message.equals("Click Return button because this page has done")) {
            return "Press BACK button";
        } else if (message.equals("")) {
            return "";
        } else if (message.contains("_") && !message.contains("implicit")) {
            String[] ms = message.split("\\/");
            String[] ms2 = ms[1].split("\\, ");
            message = ms2[0];
            StringBuffer stringBuffer = new StringBuffer("Click");
            if (message.split("\\_").length != 0) {

                if (message.contains("_fab"))
                    stringBuffer.append(" floating button ");
                else if (message.contains("card"))
                    stringBuffer.append(" card for collection ");
                String[] parts = message.split("\\_");

                for (int i = 0; i < parts.length; i++) {
                    switch (parts[i]) {
                        case "toolbar":
                            stringBuffer.append(" top ");
                            break;
                        case "menu":
                            stringBuffer.append(" menu ");
                            break;
                        case "search":
                            stringBuffer.append(" search ");
                            break;
                        case "button":
                            stringBuffer.append(" button ");
                            break;
                        case "btn":
                            stringBuffer.append(" button ");
                            break;
                        case "close":
                            stringBuffer.append(" close ");
                            break;
                        case "tv":
                            stringBuffer.append(" textView ");
                            break;
                        case "edit":
                            stringBuffer.append(" edit ");
                    }
                }

            }
            return stringBuffer.toString();
        } else if (message.contains("contains")) {
            StringBuffer stringBuffer = new StringBuffer("Click ");
            if (message.contains("ImageButton"))
                stringBuffer.append(" image button ");
            if (message.contains("FrameLayout"))
                stringBuffer.append(" frame layout ");
            if (message.contains("LinearLayoutCompat"))
                stringBuffer.append(" linear layout ");
            if (message.contains("TextView"))
                stringBuffer.append(" text view ");
            return stringBuffer.toString();
        } else if (message.equals("Click") || message.equals("click")) {
            message = " uncovered path ";
        } else if (message.equals("implicit_back_event")) {
            message = " press BACK button ";
        } else if (message.equals("implicit_power_event")) {
            message = " in low battery";
        } else if (message.equals("implicit_launch_event")) {
            message = " press LAUNCHER button ";
        } else {
            return message;
        }
        return message;
    }


}
