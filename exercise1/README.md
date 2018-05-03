# Prepare your environment (10')

For this lab we are using a locally installed AEM 6.3 author instance [http://localhost:4502](http://localhost:4502) (admin/admin) with the AEM Forms add-on packages installed. Before we can actually start the lab we need to deploy a couple of packages to do the exercises.

## Deploy and check

### Download TL15-master.zip

* No longer needed, the unzipped file is available in your Desktop as TL15-Master
* Download the github content to your Desktop [https://github.com/mmeewis/TL15/archive/master.zip](https://github.com/mmeewis/TL15/archive/master.zip) (Right click, save link as, select Desktop)
* Unzip TL15-master.zip

### Deploy packages

Deploy the packages used to define our site (not required for AEM forms, just for this lab). 

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

## Add some pages to your site

We will add three pages under [http://localhost:4502/sites.html/content/lab-tl15/language-masters/en](http://localhost:4502/sites.html/content/lab-tl15/language-masters/en)

### Forms portal page

During the lab we will save and submit forms, the forms portal page will allow users to access their saved and submitted forms

* Create a new page and use "**Lab TL15 Content Page**" as its template", use "Portal" as the title.
* Open the page
* Search for "draft" in components and drag "Drafts and Submission" component to your page

## Forms page

The forms you create with AEM forms can be integrated into your site

* Create a new page and use "**Lab TL15 Content Page**" as its template", use "Form" as the title.
* Open the page
* Search for "form" in components and drag "AEM Form" component to your page

## Prefill page

We will prefill a form that is integrated in our site

* Create a new page and use "**Lab TL15 Prefill Content Page**" as its template", use "Prefill" as the title.
* Open the page
* Search for "form" in components and drag "AEM Form" component to your page

## Solution

The solution does not include the packages that are part of the "Deploy packges" section. Before you can deploy the exercise solution package below, you need to deploy the listed packages first.

* The solution for this exercise is available under TL15-Master/solution/tl15-solution-exercise1-1.0.zip folder on you desktop or you can donwload (righ-click/save) [tl15-solution-exercise1-1.0.zip](../solutions/tl15-solution-exercise1-1.0.zip)
* Install the package via [package manager](http://localhost:4502/crx/packmgr/index.jsp).
