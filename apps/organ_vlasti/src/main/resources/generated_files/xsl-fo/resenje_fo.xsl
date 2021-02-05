<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ra="http://resenje"
                xmlns:re="http://www.reusability"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify" >
                        Примери поступања Повереника по жалби
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" text-align="center" font-weight="bold"  >
                        ____________________________________________________________________________
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify">
                        Решење када је жалба
                        <xsl:value-of select="/ra:resenje/ra:resenje_body/ra:tip_resenja"/>
                        – налаже се:
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify">
                        <fo:inline-container inline-progression-dimension="75%">
                            <fo:block>
                                Бр.
                                <xsl:value-of select="/ra:resenje/ra:resenje_body/@broj"/>
                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container>
                            <fo:block>
                                Датум
                                <xsl:value-of select="/ra:resenje/ra:resenje_body/@content"/>
                                године.
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify" padding-top="10px"  >
                        <xsl:value-of select="/ra:resenje/ra:resenje_body/ra:uvodne_informacije"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="16px" text-align="center" padding-top="10px"  >
                        Р Е Ш Е Њ Е
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify"  >
                        <xsl:for-each select="/ra:resenje/ra:resenje_body/ra:podaci_o_resenju/ra:tacka">
                            <fo:block>
                                <xsl:value-of select="."/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="16px" text-align="center" padding-top="10px">
                        О Б Р А З Л О Ж Е Њ Е
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify" >
                        <xsl:for-each select="/ra:resenje/ra:resenje_body/ra:podaci_o_obrazlozenju/*">
                            <xsl:if test="position() > 1">
                                <fo:block>
                                    <xsl:value-of select="."/>
                                </fo:block>
                            </xsl:if>
                        </xsl:for-each>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="13px" text-align="justify" padding-top="10px">
                        <fo:inline-container inline-progression-dimension="75%">
                            <fo:block>

                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container>
                            <fo:block>
                                ПОВЕРЕНИК
                            </fo:block>
                            <fo:block>
                                <xsl:value-of select="/ra:resenje/ra:resenje_body/ra:poverenik"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
