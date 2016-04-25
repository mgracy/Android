#!/usr/bin/env python

# from passbook.models import Pass, Barcode, StoreCard

# cardInfo = StoreCard()
# cardInfo.addPrimaryField('name', 'John Doe', 'Name')

# organizationName = 'Your organization' 
# passTypeIdentifier = 'pass.com.your.organization' 
# teamIdentifier = 'AGK5BZEN3E'

# passfile = Pass(cardInfo, \
#     passTypeIdentifier=passTypeIdentifier, \
#     organizationName=organizationName, \
#     teamIdentifier=teamIdentifier)
# passfile.serialNumber = '1234567' 
# passfile.barcode = Barcode(message = 'Barcode message')    

# # Including the icon and logo is necessary for the passbook to be valid.
# passfile.addFile('icon.png', open('images/icon.png', 'r'))
# passfile.addFile('logo.png', open('images/logo.png', 'r'))
# passfile.create('certificate.pem', 'key.pem', 'wwdr.pem', '123456', 'test.pkpass') 
# Create and output the Passbook file (.pkpass) 