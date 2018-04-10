# Prepare your environment

For this lab we are using a locally installed AEM author instance [http://localhost:4502](http://localhost:4502). Before we can actually start the lab we need to deploy a couple of packages.

## Deploy and check

### Download TL15-master.zip

* Download the github content to your Desktop [https://github.com/mmeewis/TL15/archive/master.zip](https://github.com/mmeewis/TL15/archive/master.zip) (Right click, save link as, select Desktop)
* Unzip TL15-master.zip

### Deploy packages

Deploy the packages that define our site (not required for AEM forms, just for this lab)

* Navigate to [Package Manager](http://localhost:4502/crx/packmgr/index.jsp)
* Install branding wizard
     * click "Upload Package", browse to Desktop/TL15-master/install, select "branding-wizard-2.10.3.zip", Install
* Install bootstrap
     * click "Upload Package", browse to Desktop/TL15-master/install, select "website-bootstrap-creative.package-2.4.0.zip", Install
* Install lab site
     * click "Upload Package", browse to Desktop/TL15-master/install, select "lab-tl15-full-site-1.4.zip", Install

### Check site

* Open our site's homepage [http://localhost:4502/editor.html/content/lab-tl15/language-masters/en.html](http://localhost:4502/editor.html/content/lab-tl15/language-masters/en.html), a Summit home page should display

### Check Adobe Campaign api call

In our lab we will access Adobe Campaign, let's check if you can execute an adobe.io call towards Adobe Campaign.

* In your website, navigate to the [http://localhost:4502/content/lab-tl15/language-masters/en/adobe-campaign-check.html](http://localhost:4502/content/lab-tl15/language-masters/en/adobe-campaign-check.html) and follow instructions.
