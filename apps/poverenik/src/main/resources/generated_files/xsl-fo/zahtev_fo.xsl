<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zcir="http://www.zahtevcir"
                xmlns:re="http://www.reusability"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block font-family="Times New Roman" font-size="14px" text-align="center">
                        <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:ciljani_organ_vlasti"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="15px" text-align="center">
                        ...............................................................................................................
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12px" text-align="center">
                         назив и седиште органа коме се захтев упућује
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="center" font-size="20px" font-weight="bold" padding-top="50px">
                        З А Х Т Е В
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="center" font-size="14px" font-weight="bold" padding-top="5px">
                        за приступ информацији од јавног значаја
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="justify" font-size="11px"  padding-top="15px" >
                        На основу члана 15. ст. 1. Закона о слободном приступу информацијама од јавног значаја
                        („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), од горе наведеног органа захтевам:*
                    </fo:block>

                    <fo:block-container margin-left="10px" padding-top="10px">
                        <fo:block font-family="Times New Roman" font-size="11px">
                        <xsl:for-each select="/zcir:zahtev/zcir:zahtev_body/zcir:tekst_zahteva/re:opcije/re:opcija">
                            <fo:block>
                                <xsl:if test="@izabran='true'">
                                            <fo:inline-container  inline-progression-dimension="5%">
                                                <fo:block font-family="Times New Roman" padding-top="5px">
                                                    <fo:instream-foreign-object>
                                                        <svg width="5" height="5" >
                                                            <rect width="5" height="5" style="fill:rgb(0,0,0)" />
                                                        </svg>
                                                    </fo:instream-foreign-object>
                                                </fo:block>
                                            </fo:inline-container>
                                            <fo:inline-container inline-progression-dimension="95%">
                                                <fo:block>
                                                    <xsl:value-of select="."/>
                                                </fo:block>
                                            </fo:inline-container>
                                </xsl:if>
                            </fo:block>
                            <fo:block>
                                <xsl:if test="@izabran='false'">
                                    <fo:inline-container  inline-progression-dimension="5%">
                                        <fo:block font-family="Times New Roman" padding-top="5px">
                                            <fo:instream-foreign-object>
                                                <svg width="5" height="5" >
                                                    <rect width="5" height="5" style="fill:rgb(255,255,255);stroke:rgb(0,0,0);" />
                                                </svg>
                                            </fo:instream-foreign-object>
                                        </fo:block>
                                    </fo:inline-container>
                                    <fo:inline-container inline-progression-dimension="95%">
                                        <fo:block>
                                            <xsl:value-of select="."/>
                                        </fo:block>
                                    </fo:inline-container>
                                </xsl:if>
                            </fo:block>
                            </xsl:for-each>
                        </fo:block>
                    </fo:block-container>

                    <fo:block-container margin-left="20px" >
                        <fo:block font-family="Times New Roman" font-size="11px">
                            <xsl:for-each select="/zcir:zahtev/zcir:zahtev_body/zcir:tekst_zahteva/re:opcije/re:nacini_dostave/re:nacin_dostave">
                                <fo:block>
                                    <xsl:if test="@izabran='true'">
                                        <fo:inline-container  inline-progression-dimension="5%">
                                            <fo:block font-family="Times New Roman" padding-top="5px">
                                                <fo:instream-foreign-object>
                                                    <svg width="5" height="5" >
                                                        <rect width="5" height="5" style="fill:rgb(0,0,0)" />
                                                    </svg>
                                                </fo:instream-foreign-object>
                                            </fo:block>
                                        </fo:inline-container>
                                        <fo:inline-container inline-progression-dimension="95%">
                                            <fo:block>
                                                <xsl:value-of select="."/>
                                            </fo:block>
                                        </fo:inline-container>
                                    </xsl:if>
                                </fo:block>
                                <fo:block>
                                    <xsl:if test="@izabran='false'">
                                        <fo:inline-container  inline-progression-dimension="5%">
                                            <fo:block font-family="Times New Roman" padding-top="5px">
                                                <fo:instream-foreign-object>
                                                    <svg width="5" height="5" >
                                                        <rect width="5" height="5" style="fill:rgb(255,255,255);stroke:rgb(0,0,0);" />
                                                    </svg>
                                                </fo:instream-foreign-object>
                                            </fo:block>
                                        </fo:inline-container>
                                        <fo:inline-container inline-progression-dimension="95%">
                                            <fo:block>
                                                <xsl:value-of select="."/>
                                                <xsl:if test="position() = last()">
                                                    <xsl:value-of select="/re:nacin_dostave_input"/>
                                                </xsl:if>
                                            </fo:block>
                                        </fo:inline-container>
                                    </xsl:if>
                                </fo:block>
                            </xsl:for-each>
                        </fo:block>
                    </fo:block-container>

                    <fo:block font-family="Times New Roman" margin-left="10px" font-size="11px"  padding-top="15px" >
                        Овај захтев се односи на следеће информације:
                    </fo:block>

                    <fo:block font-family="Times New Roman" margin-left="10px" font-size="11px"  padding-top="5px" >
                        <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:tekst_zahteva/re:informacija_o_zahtevu"/>
                    </fo:block>

                    <fo:block padding-top="20px">
                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block font-family="Times New Roman" text-align="justify" font-size="11px" padding-top="10px">
                                У <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:mesto"/>,
                            </fo:block>
                            <fo:block font-family="Times New Roman" text-align="justify" font-size="11px" padding-top="10px">
                                <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/@content"/> године.
                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container font-family="Times New Roman" text-align="center" font-size="11px" inline-progression-dimension="50%">
                            <fo:block padding-top="10px">
                                <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:informacije_o_traziocu/re:lice"/>
                            </fo:block>
                            <fo:block padding-top="10px">
                                <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:informacije_o_traziocu/re:adresa"/>
                                <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:informacije_o_traziocu/re:adresa/re:ulica/@broj"/>
                            </fo:block>
                            <fo:block padding-top="10px">
                                <xsl:value-of select="/zcir:zahtev/zcir:zahtev_body/zcir:informacije_o_traziocu/re:drugi_podaci_za_kontakt"/>
                            </fo:block>
                            <fo:block padding-top="10px">
                                _______________________
                            </fo:block>
                            <fo:block>
                                Потпис
                            </fo:block>

                        </fo:inline-container>
                    </fo:block>
                    <fo:block margin-top="30px">
                        ________________________________________________________________
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="9px">
                        * У кућици означити која законска права на приступ информацијама желите да остварите.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="9px">
                        ** У кућици означити начин достављања копије докумената.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="9px">
                        *** Када захтевате други начин достављања обавезно уписати који начин достављања захтевате.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
