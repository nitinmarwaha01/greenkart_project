package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.BaseClass;

public class GreenKart_POM extends BaseClass {
	
	public GreenKart_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.product-action button")
	private List<WebElement> addToCart; 
	
	@FindBy(css="h4.product-name")
	private List<WebElement> productNames; 
	
	@FindBy(css="a.increment")
	private List<WebElement> addVegetableIcon; 
	
	@FindBy(css="div.stepper-input input")
	private List<WebElement> vegetablesQuantity; 
	
	@FindBy(css="a.cart-icon img")
	private WebElement cartIcon;
	
	@FindBy(css="input.promoCode")
	private WebElement promoCode;
	
	@FindBy(css="div.action-block button")
	private WebElement proceedToCheckOut;
	
	@FindBy(css="button.promoBtn")
	private WebElement applyPromoCode;
	
	@FindBy(xpath="//button[contains(text(),'Place Order')]")
	private WebElement placeOrder;
	
	@FindBy(xpath="//button[contains(text(),'Proceed')]")
	private WebElement proceed;
	
	@FindBy(xpath="//div[@class='products']/div/div/select")
	private WebElement Country;
	
	@FindBy(css="input.chkAgree")
	private WebElement checkboxAgreeTandC;
	
	public List<WebElement> getAddToCartLinks(){
		return addToCart;
	}
	
	public List<WebElement> getProductNames(){
		return productNames;
	}
	
	public List<WebElement> getAddVegetableIcons(){
		return addVegetableIcon;
	}
	
	public List<WebElement> getVegetablesQuantity(){
		return vegetablesQuantity;
	}
	
	public WebElement cartIcon() {
		return cartIcon;
	}
	
	public WebElement applyPrompCodeButton() {
		return applyPromoCode;
	}
	
	public WebElement proceedToCheckOut() {
		return proceedToCheckOut;
	}
	
	public WebElement promoCode() {
		return promoCode;
	}
	
	public WebElement placeOrder() {
		return placeOrder;
	}
	
	public WebElement agreeTermsAndConditions() {
		return checkboxAgreeTandC;
	}
	
	public WebElement proceed() {
		return proceed;
	}
	
	public Select Country() {
		Select country = new Select(Country);
		return country;
	}
}
