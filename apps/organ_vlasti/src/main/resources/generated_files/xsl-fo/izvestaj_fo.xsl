<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns3="http://izvestaji"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        Извештај
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="20px" font-weight="bold" padding-top="20px" text-align="center">
                        Захтеви
                    </fo:block>

                    <fo:block padding-bottom="15px">
                        <fo:table font-family="Times New Roman" border="1px">
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Поднети захтеви</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"   color="black" padding="10px" font-weight="bold">
                                        <fo:block>Прихваћени</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Одбијени</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                    <fo:table-row border="1px solid darkgrey">
                                        <fo:table-cell padding="10px">
                                            <fo:block>
                                                <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zahtevi_podneti" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="10px">
                                            <fo:block>
                                                <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zahtevi_prihvaceni" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="10px">
                                            <fo:block>
                                                <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zahtevi_odbijeni" />
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="20px" font-weight="bold" padding="10px" text-align="center">
                        Жалбе на одлуку
                    </fo:block>
                    <fo:block padding-bottom="15px">
                        <fo:table font-family="Times New Roman"  border="1px">
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Поднете</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"   color="black" padding="10px" font-weight="bold">
                                        <fo:block>Прихваћене</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Одбијене</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Поништене</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_podneti" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_prihvaceno" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_odbijeno" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_odluke_ponisteno" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="20px" font-weight="bold" padding="10px" text-align="center">
                        Жалбе на ћутање
                    </fo:block>
                    <fo:block padding-bottom="15px">
                        <fo:table font-family="Times New Roman" border="1px">
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Поднете</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"   color="black" padding="10px" font-weight="bold">
                                        <fo:block>Прихваћене</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Одбијене</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#dbdbdb"  color="black" padding="10px" font-weight="bold">
                                        <fo:block>Поништене</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_podneti" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_prihvaceno" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_odbijeno" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="/ns3:izvestaj/ns3:izvestaj_body/ns3:zalbe_cutanje_ponisteno" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
