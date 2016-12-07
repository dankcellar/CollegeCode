#include <linux/init.h>
#include <linux/module.h>
#include <linux/device.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <asm/uaccess.h>

#define DEVICE_NAME "charDev"
#define CLASS_NAME "char"

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Eric, Jonathan, Sean");
MODULE_DESCRIPTION("Character Device Driver");
MODULE_VERSION("1.0");

static int majorDevicenum;
static int numOfOpens = 0;
static char input[1024] = {0};
static short message_size;
static struct class* charDevClass = NULL;
static struct device* charDevDevice = NULL;

static DEFINE_MUTEX(charDev_Mutex);

static int dev_open(struct inode *, struct file *);
static int dev_release(struct inode *, struct file *);
static ssize_t dev_read(struct file *, char *, size_t len, loff_t *offset);
static ssize_t dev_write(struct file *, const char *, size_t len, loff_t *offset);

static struct file_operations flops = {
	.open = dev_open,
	.read = dev_read,
	.write = dev_write,
	.release = dev_release,
};

static int __init charDev_init(void) {

	printk(KERN_INFO "charDev: Initializing the charDev LKM\n");
	majorDevicenum = register_chrdev(0, DEVICE_NAME, &flops);

	if(majorDevicenum < 0) {
		printk(KERN_ALERT "charDev failed to register a major number\n");
		return majorDevicenum;
	}

	printk(KERN_INFO "charDev: Registered correctly with major number %d\n", majorDevicenum);
	charDevClass = class_create(THIS_MODULE, CLASS_NAME);

	if(IS_ERR(charDevClass)) {
		unregister_chrdev(majorDevicenum, DEVICE_NAME);
		printk(KERN_ALERT "Failed to resger device class\n");
		return PTR_ERR(charDevClass);
	}

	printk(KERN_INFO "charDev: Device class registered correctly\n");
	charDevDevice = device_create(charDevClass, NULL, MKDEV(majorDevicenum, 0), NULL, DEVICE_NAME);

	if(IS_ERR(charDevDevice)) {
		class_destroy(charDevClass);
		unregister_chrdev(majorDevicenum, DEVICE_NAME);
		printk(KERN_ALERT "charDev: Failed to create the device\n");
		return PTR_ERR(charDevDevice);
	}

	printk(KERN_INFO "charDev: Device class created correctly\n");
	mutex_init(&charDev_Mutex);
	return 0;
}

static void __exit charDev_exit(void) {

	mutex_destroy(&charDev_Mutex);
	device_destroy(charDevClass, MKDEV(majorDevicenum, 0));
	class_unregister(charDevClass);
	class_destroy(charDevClass);
	unregister_chrdev(majorDevicenum, DEVICE_NAME);
	printk(KERN_INFO "charDev: Exit\n");
}

static int dev_open(struct inode *inodep, struct file *filep) {

	if(!mutex_trylock(&charDev_Mutex)){
		printk(KERN_ALERT "charDev: Device is in use by another process");
		return -EBUSY;
	}
	numOfOpens++;
	printk(KERN_INFO "charDev: Device has been opened %d time(s)\n", numOfOpens);
	return 0;
}

static ssize_t dev_read(struct file *filep, char *buffer, size_t len, loff_t *offset) {

	int error_count = 0;
	unsigned long N = 1024;
	error_count = copy_to_user(buffer, input, N);

	if(error_count == 0) {
		printk(KERN_INFO "charDev: Sent %d characters to the user\n", message_size);
		return(message_size = 0);
	} else {
		printk(KERN_INFO "charDev: Failed to send %d characters to the user\n", error_count);
		return -EFAULT;
	}
}

static ssize_t dev_write(struct file *filep, const char *buffer, size_t len, loff_t *offset) {

	sprin(input, "%s(%d letters)", buffer, len);
	message_size = strlen(input);
	printk(KERN_INFO "charDev: Received %d characters from the user\n", len);
	return len;

}

static int dev_release(struct inode *inodep, struct file *filep) {

	mutex_unlock(&charDev_Mutex);
	printk(KERN_INFO "charDev: Device successfully closed \n");
	return 0;
}

module_init(charDev_init);
module_exit(charDev_exit);
