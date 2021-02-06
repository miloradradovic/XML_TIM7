<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns3="http://izvestaji" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Bookstore (XSLT)</title>
                <style type="text/css">
                    table {
                    font-family: serif;
                        border-collapse: collapse;
                        margin: 50px auto 50px auto;
                        width: 90%;
                    }
                    th, td {
                        text-align: center;
                        padding: 30px;
                    }
                    tr:nth-child(even){ background-color: #f2f2f2 }
                    th {
                        background-color: #dbdbdb;
                        font-family: sans-serif;
                        color: black;
                    }
                    tr { border: 1px solid darkgrey; }
                    body { font-family: sans-serif; }
                    p { text-indent: 30px;font-family: sans-serif }
                    .sup {
                        vertical-align: super;
                        padding-left: 4px;
                        font-size: small;
                        text-transform: lowercase;
                    }
                    
                </style>
            </head>
            <body>
                <h1 style="text-align:center">Извештај</h1>
                <h2 style="text-align:center;padding-top:15px">Захтеви</h2>
                <table style="border: 1px">
                    <tr bgcolor="#9acd32">
                        <th>Поднети</th>
                        <th>Прихваћени</th>
                        <th>Одбијени</th>
                    </tr>
                    <tr>
                            <td>
                                <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zahtevi_podneti" />
                            </td>

                            <td>
                                <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zahtevi_prihvaceni" />
                            </td>
                            <td>
                                <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zahtevi_odbijeni" />
                            </td>
                        </tr>
                </table>

                <h2 style="text-align:center">Жалбе на одлуку</h2>
                <table style="border: 1px">
                    <tr bgcolor="#9acd32">
                        <th>Поднете</th>
                        <th>Прихваћене</th>
                        <th>Одбијене</th>
                        <th>Поништене</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_podneti" />
                        </td>

                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_prihvaceno" />
                        </td>
                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_odbijeno" />
                        </td>
                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_ponisteno" />
                        </td>
                    </tr>
                </table>

                <h2 style="text-align:center">Жалбе на ћутање</h2>
                <table style="border: 1px">
                    <tr bgcolor="#9acd32">
                        <th>Поднете</th>
                        <th>Прихваћене</th>
                        <th>Одбијене</th>
                        <th>Поништене</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_podneti" />
                        </td>

                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_prihvaceno" />
                        </td>
                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_odbijeno" />
                        </td>
                        <td>
                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_ponisteno" />
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
