When designed at scale, wireless sensor systems are notoriously difficult to test, debug,and profile. Network simulators have proven useful, but are unable to capture the complex dynamics of wireless signal propagation and interference, nor the subtleties of underlying hardware components; physical experimentation remains a necessity. To reduce the expense of large-scale experimental work, both in setup effort and equipment costs, researchers increasingly depend on fixed network deployments shared through a software infrastructure that assists in system configuration, software deployment, and data collection. Several such _sensor network testbeds_ are under development across the world.

We present an alternative testbed architecture that complements existing efforts. The novelty of the design and the resulting benefits stem from three important characteristics absent from other designs.  First, the system is _interactive_; users can profile both
source- and network-level components across a network in realtime.  Second, the system is _source-centric_; it enables automated source code analysis, instrumentation, and compilation.  Finally, the system design is _open_; developers can extend the set of exposed interfaces as appropriate to particular projects without modifying the underlying middleware.

Automatically exported from code.google.com/p/nestbed