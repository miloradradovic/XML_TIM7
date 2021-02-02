<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:oba="http://www.obavestenje"
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
                    <fo:block font-family="Times New Roman" font-size="13px" padding="10px">
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:naziv_organa[@sediste]"/>
                    </fo:block>
                    <fo:block text-indent="10px" font-family="Times New Roman">

                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="20px" font-weight="bold" padding="10px">
                        Available books:
                    </fo:block>
                    <fo:block text-indent="10px">
                        Highlighting (*) the book titles with a
                        <fo:inline font-weight="bold">price less than $40</fo:inline>.
                    </fo:block>
                    <fo:block>

                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
