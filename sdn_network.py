from mininet.net import Mininet
from mininet.node import Controller, OVSSwitch, RemoteController
from mininet.cli import CLI
def create_topology():
    net = Mininet(controller=Controller, switch=OVSSwitch)

    # Add a controller
    c0 = net.addController('c0')
    # Add switches and hosts
    s1 = net.addSwitch('s1')
    s2 = net.addSwitch('s2')
    s3 = net.addSwitch('s3')

    h1 = net.addHost('h1')
    h2 = net.addHost('h2')
    h3 = net.addHost('h3')
    h4 = net.addHost('h4')
    h5 = net.addHost('h5')
    h6 = net.addHost('h6')

    # Link hosts to switches
    net.addLink(h1, s1)
    net.addLink(h2, s1)
    net.addLink(h3, s2)
    net.addLink(h4, s2)
    net.addLink(h5, s3)
    net.addLink(h6, s3)

    # Link switches together
    net.addLink(s1, s2)
    net.addLink(s2, s3)

    net.start()
    CLI(net)
    net.stop()

if __name__ == '__main__':
    create_topology()
