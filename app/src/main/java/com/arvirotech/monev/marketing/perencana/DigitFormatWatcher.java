//package com.arvirotech.monev.marketing.perencana;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.Locale;
//
//public class DigitFormatWatcher implements TextWatcher {
//
//    private TextView pagu;
//    private String lastText;
//    private boolean bDel = false;
//    private boolean bInsert = false;
//    private int pos;
//    String processed = "";
//
////    public DigitFormatWatcher(TextView pagu) {
////        this.pagu = pagu;
////    }
////
////    public static String getStringWithSeparator(long value){
////        DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
////        String f = formatter.format(value);
////        return f;
////    }
////
////    @Override
////    public void onTextChanged(CharSequence s, int start, int before, int count) {
////        bDel = false;
////        bInsert = false;
////        if (before == 1 && count == 0) {
////            bDel = true;
////            pos = start;
////        } else if (before == 0 && count == 1) {
////            bInsert = true;
////            pos = start;
////        }
////    }
////
////    @Override
////    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////        lastText = s.toString();
////    }
////
////    @Override
////    public void afterTextChanged(Editable s) {
////        String initial = pagu.toString();
////
////        if (pagu == null) return;
////        if (initial.isEmpty()) return;
////        String cleanString = initial.replace(",", "");
////
////        NumberFormat formatter = new DecimalFormat("#,###");
////
////        double myNumber = new Double(cleanString);
////
////        processed = formatter.format(myNumber);
////
////        //Remove the listener
////        pagu.removeTextChangedListener(this);
////
////        //Assign processed text
////        pagu.setText(processed);
////
//////        try {
//////            pagu.setSelection(processed.length());
//////        } catch (Exception e) {
//////            // TODO: handle exception
//////        }
////
////        //Give back the listener
////        pagu.addTextChangedListener(this);
////    }
//
//    private void setCurrency(final EditText edt) {
//        edt.addTextChangedListener(new TextWatcher() {
//            private String current = "";
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!s.toString().equals(current)) {
//                    edt.removeTextChangedListener(this);
//
//                    Locale local = new Locale("id", "id");
//                    String replaceable = String.format("[Rp,.\\s]",
//                            NumberFormat.getCurrencyInstance().getCurrency()
//                                    .getSymbol(local));
//                    String cleanString = s.toString().replaceAll(replaceable,
//                            "");
//
//                    double parsed;
//                    try {
//                        parsed = Double.parseDouble(cleanString);
//                    } catch (NumberFormatException e) {
//                        parsed = 0.00;
//                    }
//
//                    NumberFormat formatter = NumberFormat
//                            .getCurrencyInstance(local);
//                    formatter.setMaximumFractionDigits(0);
//                    formatter.setParseIntegerOnly(true);
//                    String formatted = formatter.format((parsed));
//
//                    String replace = String.format("[Rp\\s]",
//                            NumberFormat.getCurrencyInstance().getCurrency()
//                                    .getSymbol(local));
//                    String clean = formatted.replaceAll(replace, "");
//
//                    current = formatted;
//                    edt.setText(clean);
//                    edt.setSelection(clean.length());
//                    edt.addTextChangedListener(this);
//                }
//            }
//        });
//    }
//}
