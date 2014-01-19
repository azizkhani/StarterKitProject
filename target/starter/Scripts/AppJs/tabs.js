
function Tabs() { }

//Tabs.removeSelectedRegex = new RegExp("(^|\\s)linkSelected(\\s|$)", 'g');
//Tabs.removeUnselectedRegex = new RegExp("(^|\\s)linkUnselected(\\s|$)", 'g');

/**
* 
*/
Tabs.init = function (tabListId) {
    if (document.getElementById(tabListId) == null)
        return false;
    Tabs.tabLinks = document.getElementById(tabListId).getElementsByTagName("A");

    var link, tabId, tab;
    for (var i = 0; i < Tabs.tabLinks.length; i++) {
        link = Tabs.tabLinks[i];
        tabId = link.getAttribute("tabId");
        if (!tabId) alert("Expand link does not have a tabId element: " + link.innerHTML);
        tab = document.getElementById(tabId);
        if (!tab) alert("tabId does not exist: " + tabId);

        if (i == 0) {
            tab.style.display = "block";
            link.className = "linkSelected "; //+ link.className.replace(Tabs.removeUnselectedRegex, '');
        }
        else {
            tab.style.display = "none";
            link.className = "linkUnselected "; //+ link.className.replace(Tabs.removeSelectedRegex, '');
        }

        link.onclick = function () {
            var tabId = this.getAttribute("tabId");
            for (var i = 0; i < Tabs.tabLinks.length; i++) {
                var link = Tabs.tabLinks[i];
                var loopId = link.getAttribute("tabId");
                if (loopId == tabId) {
                    document.getElementById(loopId).style.display = "block";
                    link.className = "linkSelected "; //+ link.className.replace(Tabs.removeUnselectedRegex, '');
                }
                else {
                    document.getElementById(loopId).style.display = "none";
                    link.className = "linkUnselected "; //+ link.className.replace(Tabs.removeSelectedRegex, '');
                }
            }
            if (this.blur) this.blur();
            return false;
        }
    }
}


/* ----------------------- Search -----------------------------*/

function SearchTabs() { }

//Tabs.removeSelectedRegex = new RegExp("(^|\\s)linkSelected(\\s|$)", 'g');
//Tabs.removeUnselectedRegex = new RegExp("(^|\\s)linkUnselected(\\s|$)", 'g');

/**
* 
*/
SearchTabs.init = function (tabListId) {
    if (document.getElementById(tabListId) == null)
        return false;

    SearchTabs.tabLinks = document.getElementById(tabListId).getElementsByTagName("A");

    var link, tabId, tab;
    for (var i = 0; i < SearchTabs.tabLinks.length; i++) {
        link = SearchTabs.tabLinks[i];
        tabId = link.getAttribute("tabId");
        if (!tabId) alert("Expand link does not have a tabId element: " + link.innerHTML);
        tab = document.getElementById(tabId);
        if (!tab) alert("tabId does not exist: " + tabId);

        if (i == 0) {
            tab.style.display = "block";
            link.className = "linkSelected "; //+ link.className.replace(SearchTabs.removeUnselectedRegex, '');
        }
        else {
            tab.style.display = "none";
            link.className = "linkUnselected "; //+ link.className.replace(SearchTabs.removeSelectedRegex, '');
        }

        link.onclick = function () {
            var tabId = this.getAttribute("tabId");
            for (var i = 0; i < SearchTabs.tabLinks.length; i++) {
                var link = SearchTabs.tabLinks[i];
                var loopId = link.getAttribute("tabId");
                if (loopId == tabId) {
                    document.getElementById(loopId).style.display = "block";
                    link.className = "linkSelected "; //+ link.className.replace(SearchTabs.removeUnselectedRegex, '');
                }
                else {
                    document.getElementById(loopId).style.display = "none";
                    link.className = "linkUnselected "; //+ link.className.replace(SearchTabs.removeSelectedRegex, '');
                }
            }
            if (this.blur) this.blur();
            return false;
        }
    }
}
