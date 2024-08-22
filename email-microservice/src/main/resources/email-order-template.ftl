<#-- email-order-template.ftl -->
Hallo ${name},

Wir möchten Sie darüber informieren, dass Ihre Bestellung ${orderNumber}, vom ${date}, erfolgreich bearbeitet wurde.

Details Ihrer Bestellung:
<#list items as item>
- ${item}
</#list>

Vielen Dank für Ihren Einkauf!

Mit freundlichen Grüßen,
Ihr Team von Rock Solid Remedies