import numpy as np
import matplotlib.pyplot as plt

def algorithm1(value):
    return ((475 * value) - 1331)

def algorithmRick(value):
    return (((2347110.773 * (18.06302774 * 0.001) ** (value) + (2 * (4.65 - value))) / (8 * value)) ** 2.2) + (10 * value) + 30 - value

#(2347110.773 * (18.06302774 * 0.001) ** value + (3 * (4.65 - value)))
#(((2347110.773 * (18.06302774 * 0.001) ** value + (3 * (4.65 - value))) / (5 * value)) ** 2) + (10 * value)


# FINAL: (((2347110.773 * (18.06302774 * 0.001) ** (value) + (2 * (4.65 - value))) / (8 * value)) ** 2.2) + (10 * value) + 30 - value

def algorithmAngel(value):
    return (((4.65 - value) ** (value +1)) * 10)

#voltageA0 = [3.02, 2.97, 2.99, 3.27]
#glucoseA0 = [219.05, 197.25, 192.25, 347.4]

voltageA0 = np.array([3.02, 2.97, 2.99, 3.27, 2.95])
glucoseA0 = np.array([219.05, 197.25, 192.25, 347.4, 187.56])

#voltageA1 = [2.51, 2.56, 2.54, 2.32]
#glucoseA1 = [-30.99, -8.64, -15.91, -124.91]

voltageA1 = np.array([2.51, 2.56, 2.54, 2.32, 2.53])
glucoseA1 = np.array([-30.99, -8.64, -15.91, -124.91, -20.76])

voltage = np.linspace(2.2, 2.8, num=2000) # num es la resolucion
#ultraMg = [99,81,100,256]
ultraMg = np.array([99, 81, 100, 256, 118])



plt.figure(figsize=(10, 10))
#plt.subplot(2,1,1)
plt.subplot(121)

plt.plot(voltageA0, ultraMg, '.b', label = "ultraMg")
plt.legend(loc='best')

plt.plot(voltageA0, glucoseA0, '.r', label = "glucoseA0")
plt.legend(loc='best')

#plt.plot(voltageA0, algorithm1(voltageA0), '.g', label = "Algorithm 1")
#plt.legend(loc='best')

"""plt.plot(voltageA1, ultraMg,'.y', label = "ultraMg")
plt.legend(loc='best')

plt.plot(voltageA1, glucoseA1,'.g', label = "glucoseA1")
plt.legend(loc='best')"""


plt.xlabel("Voltage")
plt.ylabel("Glucose")
plt.title(" A0 ")


#plt.subplot(2,1,2)
plt.subplot(122)



#plt.plot(voltageA1, glucoseA1,'.r', label = "glucoseA1")
#plt.legend(loc='best')

plt.plot(voltage, algorithmRick(voltage),'.y', label = "Algorithm Rick")
plt.legend(loc='best')

#plt.plot(voltageA1, algorithmAngel(voltageA1),'.g', label = "Algorithm Angel")
#plt.legend(loc='best')

plt.plot(voltageA1, ultraMg,'.b', label = "ultraMg")
plt.legend(loc='best')

plt.xlabel("Voltage")
plt.ylabel("Glucose")
plt.title(" A1 ")

plt.show()
