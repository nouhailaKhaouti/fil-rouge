package com.example.filRouge.helpers.email;
import com.example.filRouge.entities.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailService {
    final private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(Member member, String password) throws MessagingException, IOException {

            MimeMessage message = mailSender.createMimeMessage();

            message.setFrom(new InternetAddress(from));
            message.setRecipients(MimeMessage.RecipientType.TO, member.getEmail());
            message.setSubject("GENERATE PASSWORD");

        String htmlContent="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title></title>\n" +
                "    <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "        a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "    <!--[if gte mso 9]>\n" +
                "    <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "            <o:AllowPNG></o:AllowPNG>\n" +
                "            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "    </xml>\n" +
                "    <![endif]-->\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div dir=\"ltr\" class=\"es-wrapper-color\">\n" +
                "    <!--[if gte mso 9]>\n" +
                "    <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "        <v:fill type=\"tile\" color=\"#fafafa\"></v:fill>\n" +
                "    </v:background>\n" +
                "    <![endif]-->\n" +
                "    <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td class=\"esd-email-paddings\" valign=\"top\">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content esd-header-popover\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"es-adaptive esd-stripe\" align=\"center\" esd-custom-block-id=\"88589\">\n" +
                "                            <table class=\"es-content-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p10\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"580\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td align=\"center\" class=\"es-infoblock esd-block-text\">\n" +
                "                                                                <p>Put your preheader text here. <a href=\"https://viewstripo.email\" class=\"view\" target=\"_blank\">View in browser</a></p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"es-adaptive esd-stripe\" align=\"center\" esd-custom-block-id=\"88593\">\n" +
                "                            <table class=\"es-header-body\" style=\"background-color: #3d5ca3;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#3d5ca3\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p20t es-p20b es-p20r es-p20l\" style=\"background-color: #3d5ca3;\" bgcolor=\"#3d5ca3\" align=\"left\">\n" +
                "                                        <!--[if mso]><table width=\"560\" cellpadding=\"0\"\n" +
                "                                                            cellspacing=\"0\"><tr><td width=\"270\" valign=\"top\"><![endif]-->\n" +
                "                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"es-m-p20b esd-container-frame\" width=\"270\" align=\"left\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-image es-m-p0l es-m-txt-c\" align=\"left\" style=\"font-size:0\"><a href=\"https://viewstripo.email\" target=\"_blank\"><img src=\"https://tlr.stripocdn.email/content/guids/CABINET_66498ea076b5d00c6f9553055acdb37a/images/12051527590691841.png\" alt style=\"display: block;\" width=\"183\"></a></td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"270\" valign=\"top\"><![endif]-->\n" +
                "                                        <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"270\" align=\"left\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td align=\"center\" class=\"esd-empty-container\" style=\"display: none;\"></td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                            <table class=\"es-content-body\" style=\"background-color: #ffffff;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p40t es-p20r es-p20l\" style=\"background-color: transparent; background-position: left top;\" bgcolor=\"transparent\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table style=\"background-position: left top;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-image es-p5t es-p5b\" align=\"center\" style=\"font-size:0\"><a target=\"_blank\"><img src=\"https://tlr.stripocdn.email/content/guids/CABINET_dd354a98a803b60e2f0411e893c82f56/images/23891556799905703.png\" alt style=\"display: block;\" width=\"175\"></a></td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text es-p15t es-p15b\" align=\"center\">\n" +
                "                                                                <h1 style=\"color: #333333; font-size: 20px;\"><strong>Your new password</strong><strong></strong></h1>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text es-p40r es-p40l\" align=\"left\">\n" +
                "                                                                <p style=\"text-align: center;\">HI,&nbsp;${name}</p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text es-p35r es-p40l\" align=\"center\">\n" +
                "                                                                <p>There was a request to create an account in our web site<br></p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text es-p25t es-p40r es-p40l\" align=\"center\">\n" +
                "                                                                <p>If did not make this request, just ignore this email. Otherwise, please use this password to enter your account ,<br></p>\n" +
                "                                                                <p>and kindly change it for security purpose:</p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-button es-p40t es-p40b es-p10r es-p10l h-auto\" align=\"center\" valign=\"middle\" height=\"131\"><span class=\"es-button-border\"><a href=\"https://viewstripo.email/\" class=\"es-button\" target=\"_blank\">\n" +
                "                                                                                                <!--[if !mso]> --><img src=\"https://demo.stripocdn.email/content/guids/c237d72c-94ab-4913-94a1-3fa614cd35c7/images/password159.png\" alt=\"icon\" width=\"16\" class=\"esd-icon-left\" style=\"margin-right:10px;\" align=\"absmiddle\">\n" +
                "                                                                <!--<![endif]--> "+password+"\n" +
                "                                                                                            </a></span></td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p20t es-p10r es-p10l\" style=\"background-position: center center;\" align=\"left\">\n" +
                "                                        <!--[if mso]><table width=\"580\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"199\" valign=\"top\"><![endif]-->\n" +
                "                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"199\" align=\"left\">\n" +
                "                                                    <table style=\"background-position: center center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text es-p15t es-m-txt-c\" align=\"right\">\n" +
                "                                                                <p style=\"font-size: 16px; color: #666666;\"><strong>Follow us:</strong></p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"361\" valign=\"top\"><![endif]-->\n" +
                "                                        <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"361\" align=\"left\">\n" +
                "                                                    <table style=\"background-position: center center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-social es-p10t es-p5b es-m-txt-c\" align=\"left\" style=\"font-size:0\">\n" +
                "                                                                <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                    <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href><img src=\"https://tlr.stripocdn.email/content/assets/img/social-icons/rounded-gray/facebook-rounded-gray.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\"></a></td>\n" +
                "                                                                        <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href><img src=\"https://tlr.stripocdn.email/content/assets/img/social-icons/rounded-gray/twitter-rounded-gray.png\" alt=\"Tw\" title=\"Twitter\" width=\"32\"></a></td>\n" +
                "                                                                        <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href><img src=\"https://tlr.stripocdn.email/content/assets/img/social-icons/rounded-gray/instagram-rounded-gray.png\" alt=\"Ig\" title=\"Instagram\" width=\"32\"></a></td>\n" +
                "                                                                        <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href><img src=\"https://tlr.stripocdn.email/content/assets/img/social-icons/rounded-gray/youtube-rounded-gray.png\" alt=\"Yt\" title=\"Youtube\" width=\"32\"></a></td>\n" +
                "                                                                        <td class=\"es-p10r\" valign=\"top\" align=\"center\"><a target=\"_blank\" href><img src=\"https://tlr.stripocdn.email/content/assets/img/social-icons/rounded-gray/linkedin-rounded-gray.png\" alt=\"In\" title=\"Linkedin\" width=\"32\"></a></td>\n" +
                "                                                                    </tr>\n" +
                "                                                                    </tbody>\n" +
                "                                                                </table>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p5t es-p20b es-p20r es-p20l\" style=\"background-position: left top;\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text\" esd-links-color=\"#666666\" align=\"center\">\n" +
                "                                                                <p style=\"font-size: 14px;\">Contact us: <a target=\"_blank\" style=\"font-size: 14px; color: #666666;\" href=\"tel:123456789\">123456789</a> | <a target=\"_blank\" href=\"mailto:universityExams@mail.com\" style=\"font-size: 14px; color: #666666;\">universityExams@mail.com</a></p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                            <table class=\"es-footer-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p10t es-p30b es-p20r es-p20l\" style=\" background-color: #0b5394; background-position: left top;\" bgcolor=\"#0b5394\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-text es-p5t es-p5b\" align=\"left\">\n" +
                "                                                                <h2 style=\"font-size: 16px; color: #ffffff;\"><strong>Have quastions?</strong></h2>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td esd-links-underline=\"none\" esd-links-color=\"#ffffff\" class=\"esd-block-text es-p5b\" align=\"left\">\n" +
                "                                                                <p style=\"font-size: 14px; color: #ffffff;\">We are here to help, learn more about us <a target=\"_blank\" style=\"font-size: 14px; color: #ffffff; text-decoration: none;\">here</a></p>\n" +
                "                                                                <p style=\"font-size: 14px; color: #ffffff;\">or <a target=\"_blank\" style=\"font-size: 14px; text-decoration: none; color: #ffffff;\">contact us</a><br></p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                            <table class=\"es-content-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p15t\" style=\"background-position: left top;\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"600\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-menu\">\n" +
                "                                                                <table class=\"es-menu\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                    <tbody>\n" +
                "                                                                    <tr class=\"links\">\n" +
                "                                                                        <td class=\"es-p10t es-p10b es-p5r es-p5l\" style=\"padding-bottom: 1px; padding-top: 0px; \" width=\"33.33%\" valign=\"top\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"color: #3D5CA3; font-size: 14px;\">Sing up</a></td>\n" +
                "                                                                        <td class=\"es-p10t es-p10b es-p5r es-p5l\" style=\"border-left: 1px solid #3d5ca3; padding-bottom: 1px; padding-top: 0px; \" width=\"33.33%\" valign=\"top\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"color: #3D5CA3; font-size: 14px;\">Blog</a></td>\n" +
                "                                                                        <td class=\"es-p10t es-p10b es-p5r es-p5l\" style=\"border-left: 1px solid #3d5ca3; padding-bottom: 1px; padding-top: 0px; \" width=\"33.33%\" valign=\"top\" bgcolor=\"transparent\" align=\"center\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"color: #3D5CA3; font-size: 14px;\">About us</a></td>\n" +
                "                                                                    </tr>\n" +
                "                                                                    </tbody>\n" +
                "                                                                </table>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"esd-block-spacer es-p20b es-p20r es-p20l\" align=\"center\" style=\"font-size:0\">\n" +
                "                                                                <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "                                                                    <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td style=\"border-bottom: 1px solid #fafafa; background: none; height: 1px; width: 100%; margin: 0px;\"></td>\n" +
                "                                                                    </tr>\n" +
                "                                                                    </tbody>\n" +
                "                                                                </table>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"esd-stripe\" style=\"background-color: #fafafa;\" bgcolor=\"#fafafa\" align=\"center\" esd-custom-block-id=\"88330\">\n" +
                "                            <table class=\"es-footer-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p15t es-p5b es-p20r es-p20l\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td esd-links-underline=\"underline\" align=\"center\" class=\"esd-block-text\">\n" +
                "                                                                <p style=\"font-size: 12px; color: #666666;\">This daily newsletter was sent to universityExams@gmail.com from company name because you subscribed. If you would not like to receive this email <a target=\"_blank\" style=\"font-size: 12px; text-decoration: underline;\" class=\"unsubscribe\">unsubscribe here</a>.</p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <table class=\"es-content esd-footer-popover\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"esd-stripe\" esd-custom-block-id=\"42537\" align=\"center\">\n" +
                "                            <table class=\"es-content-body\" style=\"background-color: transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-structure es-p30t es-p30b es-p20r es-p20l\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                        <tbody>\n" +
                "                                                        <tr>\n" +
                "                                                            <td align=\"center\" class=\"esd-empty-container\" style=\"display: none;\"></td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "</div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        message.setContent(htmlContent, "text/html; charset=utf-8");
            mailSender.send(message);
    }

}